package com.example.exception;

import com.astreya.core.starter.dto.ApiResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;

import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Path;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SuppressWarnings("unchecked")
class GlobalExceptionHandlerTest {

    @Mock
    private MessageSource messageSource;

    @Mock
    private BindingResult bindingResult;

    @Mock
    private HandlerMethod handlerMethod;

    private GlobalExceptionHandler globalExceptionHandler;

    @BeforeEach
    void setUp() {
        globalExceptionHandler = new GlobalExceptionHandler(messageSource);
        // Set default locale for consistent testing
        LocaleContextHolder.setLocale(Locale.ENGLISH);
    }

    @Test
    void handleValidationExceptions_WithSingleFieldError_ShouldReturnBadRequestResponse() {
        // Given
        String fieldName = "name";
        String errorMessage = "Name is required";
        String expectedMessage = "Validation failed";
        
        FieldError fieldError = mock(FieldError.class);
        when(fieldError.getField()).thenReturn(fieldName);
        when(fieldError.getDefaultMessage()).thenReturn(errorMessage);
        when(bindingResult.getAllErrors()).thenReturn(Arrays.asList(fieldError));
        when(messageSource.getMessage("validation.failed", new Object[]{}, "validation.failed", Locale.ENGLISH))
            .thenReturn(expectedMessage);
        
        MethodArgumentNotValidException exception = mock(MethodArgumentNotValidException.class);
        when(exception.getBindingResult()).thenReturn(bindingResult);

        // When
        ResponseEntity<ApiResponse> response = globalExceptionHandler.handleValidationExceptions(exception);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        
        ApiResponse apiResponse = response.getBody();
        assertFalse(apiResponse.isSuccess());
        assertEquals(expectedMessage, apiResponse.getMessage());
        // Note: ApiResponse status is handled by ResponseEntity status code
        
        // Verify error details
        Map<String, String> errors = (Map<String, String>) apiResponse.getData();
        assertNotNull(errors);
        assertEquals(1, errors.size());
        assertEquals(errorMessage, errors.get(fieldName));
        
        verify(messageSource).getMessage("validation.failed", new Object[]{}, "validation.failed", Locale.ENGLISH);
    }

    @Test
    void handleConstraintViolation_WithSingleViolation_ShouldReturnBadRequestResponse() {
        // Given
        String fieldName = "email";
        String errorMessage = "Email format is invalid";
        String expectedMessage = "Constraint violation";
        
        ConstraintViolation<?> violation = mock(ConstraintViolation.class);
        when(violation.getPropertyPath()).thenReturn(mock(Path.class));
        when(violation.getPropertyPath().toString()).thenReturn(fieldName);
        when(violation.getMessage()).thenReturn(errorMessage);
        
        Set<ConstraintViolation<?>> violations = Set.of(violation);
        
        when(messageSource.getMessage("constraint.violation", new Object[]{}, "constraint.violation", Locale.ENGLISH))
            .thenReturn(expectedMessage);
        
        ConstraintViolationException exception = mock(ConstraintViolationException.class);
        when(exception.getConstraintViolations()).thenReturn(violations);

        // When
        ResponseEntity<ApiResponse> response = globalExceptionHandler.handleConstraintViolation(exception);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        
        ApiResponse apiResponse = response.getBody();
        assertFalse(apiResponse.isSuccess());
        assertEquals(expectedMessage, apiResponse.getMessage());
        
        // Verify error details
        Map<String, String> errors = (Map<String, String>) apiResponse.getData();
        assertNotNull(errors);
        assertEquals(1, errors.size());
        assertEquals(errorMessage, errors.get(fieldName));
        
        verify(messageSource).getMessage("constraint.violation", new Object[]{}, "constraint.violation", Locale.ENGLISH);
    }

    @Test
    void handleTypeMismatch_WithValidParameters_ShouldReturnBadRequestResponse() {
        // Given
        String parameterName = "id";
        String requiredType = "Long";
        String expectedMessage = "Parameter 'id' must be of type Long";
        
        when(messageSource.getMessage(eq("type.mismatch"), eq(new Object[]{parameterName, requiredType}), eq("type.mismatch"), eq(Locale.ENGLISH)))
            .thenReturn(expectedMessage);
        
        MethodArgumentTypeMismatchException exception = mock(MethodArgumentTypeMismatchException.class);
        when(exception.getName()).thenReturn(parameterName);
        when(exception.getRequiredType()).thenReturn((Class) Long.class);

        // When
        ResponseEntity<ApiResponse> response = globalExceptionHandler.handleTypeMismatch(exception);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        
        ApiResponse apiResponse = response.getBody();
        assertFalse(apiResponse.isSuccess());
        assertEquals(expectedMessage, apiResponse.getMessage());
        
        verify(messageSource).getMessage(eq("type.mismatch"), eq(new Object[]{parameterName, requiredType}), eq("type.mismatch"), eq(Locale.ENGLISH));
    }

    @Test
    void handleHttpMessageNotReadable_WithValidException_ShouldReturnBadRequestResponse() {
        // Given
        String expectedMessage = "Invalid JSON format";
        
        when(messageSource.getMessage("invalid.json", new Object[]{}, "invalid.json", Locale.ENGLISH))
            .thenReturn(expectedMessage);
        
        HttpMessageNotReadableException exception = mock(HttpMessageNotReadableException.class);

        // When
        ResponseEntity<ApiResponse> response = globalExceptionHandler.handleHttpMessageNotReadable(exception);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        
        ApiResponse apiResponse = response.getBody();
        assertFalse(apiResponse.isSuccess());
        assertEquals(expectedMessage, apiResponse.getMessage());
        
        verify(messageSource).getMessage("invalid.json", new Object[]{}, "invalid.json", Locale.ENGLISH);
    }

    @Test
    void handleIllegalArgumentException_WithValidException_ShouldReturnBadRequestResponse() {
        // Given
        String exceptionMessage = "ID cannot be null";
        String expectedMessage = "Invalid argument: ID cannot be null";
        
        when(messageSource.getMessage("invalid.argument", new Object[]{exceptionMessage}, "invalid.argument", Locale.ENGLISH))
            .thenReturn(expectedMessage);
        
        IllegalArgumentException exception = new IllegalArgumentException(exceptionMessage);

        // When
        ResponseEntity<ApiResponse> response = globalExceptionHandler.handleIllegalArgumentException(exception);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        
        ApiResponse apiResponse = response.getBody();
        assertFalse(apiResponse.isSuccess());
        assertEquals(expectedMessage, apiResponse.getMessage());
        
        verify(messageSource).getMessage("invalid.argument", new Object[]{exceptionMessage}, "invalid.argument", Locale.ENGLISH);
    }

    @Test
    void handleIllegalStateException_WithValidException_ShouldReturnBadRequestResponse() {
        // Given
        String exceptionMessage = "Sample with name 'Test Sample' already exists";
        String expectedMessage = "Invalid state: Sample with name 'Test Sample' already exists";
        
        when(messageSource.getMessage("invalid.state", new Object[]{exceptionMessage}, "invalid.state", Locale.ENGLISH))
            .thenReturn(expectedMessage);
        
        IllegalStateException exception = new IllegalStateException(exceptionMessage);

        // When
        ResponseEntity<ApiResponse> response = globalExceptionHandler.handleIllegalStateException(exception);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        
        ApiResponse apiResponse = response.getBody();
        assertFalse(apiResponse.isSuccess());
        assertEquals(expectedMessage, apiResponse.getMessage());
        
        verify(messageSource).getMessage("invalid.state", new Object[]{exceptionMessage}, "invalid.state", Locale.ENGLISH);
    }

    @Test
    void handleNumberFormatException_WithValidException_ShouldReturnBadRequestResponse() {
        // Given
        String exceptionMessage = "For input string: \"abc123\"";
        String expectedMessage = "Invalid number format: For input string: \"abc123\"";
        
        when(messageSource.getMessage("invalid.number.format", new Object[]{exceptionMessage}, "invalid.number.format", Locale.ENGLISH))
            .thenReturn(expectedMessage);
        
        NumberFormatException exception = new NumberFormatException(exceptionMessage);

        // When
        ResponseEntity<ApiResponse> response = globalExceptionHandler.handleNumberFormatException(exception);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        
        ApiResponse apiResponse = response.getBody();
        assertFalse(apiResponse.isSuccess());
        assertEquals(expectedMessage, apiResponse.getMessage());
        
        verify(messageSource).getMessage("invalid.number.format", new Object[]{exceptionMessage}, "invalid.number.format", Locale.ENGLISH);
    }

    @Test
    void handleNullPointerException_WithValidException_ShouldReturnInternalServerErrorResponse() {
        // Given
        String expectedMessage = "Null reference error";
        
        when(messageSource.getMessage("null.reference", new Object[]{}, "null.reference", Locale.ENGLISH))
            .thenReturn(expectedMessage);
        
        NullPointerException exception = new NullPointerException("Cannot invoke \"String.length()\" because \"str\" is null");

        // When
        ResponseEntity<ApiResponse> response = globalExceptionHandler.handleNullPointerException(exception);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNotNull(response.getBody());
        
        ApiResponse apiResponse = response.getBody();
        assertFalse(apiResponse.isSuccess());
        assertEquals(expectedMessage, apiResponse.getMessage());
        
        verify(messageSource).getMessage("null.reference", new Object[]{}, "null.reference", Locale.ENGLISH);
    }

    @Test
    void handleGenericException_WithValidException_ShouldReturnInternalServerErrorResponse() {
        // Given
        String exceptionMessage = "Database connection failed";
        String expectedMessage = "Unexpected error: Database connection failed";
        
        when(messageSource.getMessage("unexpected.error", new Object[]{exceptionMessage}, "unexpected.error", Locale.ENGLISH))
            .thenReturn(expectedMessage);
        
        RuntimeException exception = new RuntimeException(exceptionMessage);

        // When
        ResponseEntity<ApiResponse> response = globalExceptionHandler.handleGenericException(exception);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNotNull(response.getBody());
        
        ApiResponse apiResponse = response.getBody();
        assertFalse(apiResponse.isSuccess());
        assertEquals(expectedMessage, apiResponse.getMessage());
        
        verify(messageSource).getMessage("unexpected.error", new Object[]{exceptionMessage}, "unexpected.error", Locale.ENGLISH);
    }
}
