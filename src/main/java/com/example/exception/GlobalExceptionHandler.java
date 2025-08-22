package com.example.exception;

import com.astreya.core.starter.dto.ApiResponse;
import com.astreya.core.starter.util.ApiResponseUtil;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final MessageSource messageSource;

    public GlobalExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /**
     * Helper method to get localized message
     * @param messageKey the message key from properties file
     * @param args arguments for message formatting
     * @return localized message
     */
    private String getMessage(String messageKey, Object... args) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(messageKey, args, messageKey, locale);
    }



    /**
     * Handle validation exceptions from @Valid annotations
     */
        @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return ApiResponseUtil.setErrorResponse(getMessage("validation.failed"), errors, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle constraint violation exceptions
     */
        @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse> handleConstraintViolation(ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();

        for (ConstraintViolation<?> violation : violations) {
            String fieldName = violation.getPropertyPath().toString();
            String errorMessage = violation.getMessage();
            errors.put(fieldName, errorMessage);
        }

        return ApiResponseUtil.setErrorResponse(getMessage("constraint.violation"), errors, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle type mismatch exceptions (e.g., String instead of Long for ID)
     */
        @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiResponse> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String message = getMessage("type.mismatch", ex.getName(), ex.getRequiredType().getSimpleName());
        return ApiResponseUtil.setErrorResponse(message, null, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle JSON parsing exceptions
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        String message = getMessage("invalid.json");
        return ApiResponseUtil.setErrorResponse(message, null, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle IllegalArgumentException
     */
        @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ApiResponseUtil.setErrorResponse(getMessage("invalid.argument", ex.getMessage()),
                                             null, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle IllegalStateException
     */
        @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ApiResponse> handleIllegalStateException(IllegalStateException ex) {
        return ApiResponseUtil.setErrorResponse(getMessage("invalid.state", ex.getMessage()),
                                             null, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle NumberFormatException
     */
        @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<ApiResponse> handleNumberFormatException(NumberFormatException ex) {
        return ApiResponseUtil.setErrorResponse(getMessage("invalid.number.format", ex.getMessage()),
                                             null, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle NullPointerException
     */
        @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ApiResponse> handleNullPointerException(NullPointerException ex) {
        return ApiResponseUtil.setErrorResponse(getMessage("null.reference"),
                                             null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handle all other exceptions (catch-all)
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleGenericException(Exception ex) {
        String message = getMessage("unexpected.error", ex.getMessage());
        return ApiResponseUtil.setErrorResponse(message, null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
