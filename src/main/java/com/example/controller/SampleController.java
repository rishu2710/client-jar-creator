package com.example.controller;

import com.astreya.core.starter.dto.ApiResponse;
import com.astreya.core.starter.util.ApiResponseUtil;
import com.example.constants.CommonConstants;
import com.example.entity.Sample;
import com.example.service.SampleService;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
public class SampleController {

    private final SampleService sampleService;
    private final MessageSource messageSource;

    public SampleController(SampleService sampleService, MessageSource messageSource) {
        this.sampleService = sampleService;
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
     * GET /api/v1/sample - Get all samples
     * OpenAPI: Retrieve a list of all samples
     */
    @GetMapping("/sample")
    public ResponseEntity<ApiResponse> getAllSamples(
            @RequestHeader(name = CommonConstants.CustomHeaders.X_TENANT_ID) String tenantId,
            @RequestHeader(name = CommonConstants.CustomHeaders.X_USER_ID) String userId,
            @RequestHeader(name = CommonConstants.CustomHeaders.X_AUTH_TOKEN) String authorization) {
        try {
            List<List<Sample>> samples = sampleService.findAll();
            return ApiResponseUtil.setSuccessResponse(getMessage("samples.retrieved.success"), samples);
        } catch (Exception e) {
            return ApiResponseUtil.setErrorResponse(getMessage("unexpected.error", e.getMessage()), 
                                                 null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * GET /api/v1/sample/{id} - Get sample by ID
     * OpenAPI: Retrieve a specific sample by its ID
     */
    @GetMapping("/sample/{id}")
    public ResponseEntity<ApiResponse> getSampleById(
            @PathVariable Long id,
            @RequestHeader(name = CommonConstants.CustomHeaders.X_TENANT_ID) String tenantId,
            @RequestHeader(name = CommonConstants.CustomHeaders.X_USER_ID) String userId,
            @RequestHeader(name = CommonConstants.CustomHeaders.X_AUTH_TOKEN) String authorization) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException(getMessage("sample.invalid.id"));
        }
        
        List<Sample> samples = sampleService.findById(id);
        if (samples != null && !samples.isEmpty()) {
            return ApiResponseUtil.setSuccessResponse(getMessage("sample.retrieved.success"), samples);
        } else {
            throw new IllegalArgumentException(getMessage("sample.not.found", id));
        }
    }

    /**
     * POST /api/v1/sample - Create new sample
     * OpenAPI: Create a new sample
     */
    @PostMapping("/sample")
    public ResponseEntity<ApiResponse> createSample(
            @RequestBody Sample sample,
            @RequestHeader(name = CommonConstants.CustomHeaders.X_TENANT_ID) String tenantId,
            @RequestHeader(name = CommonConstants.CustomHeaders.X_USER_ID) String userId,
            @RequestHeader(name = CommonConstants.CustomHeaders.X_AUTH_TOKEN) String authorization) {
        if (sample == null) {
            throw new IllegalArgumentException(getMessage("sample.data.null"));
        }
        
        if (sample.getName() == null || sample.getName().trim().isEmpty()) {
            throw new IllegalArgumentException(getMessage("sample.name.required"));
        }
        
        // Check if sample with same name already exists
        if (sampleService.existsByName(sample.getName())) {
            throw new IllegalStateException(getMessage("sample.duplicate.name", sample.getName()));
        }
        
        Sample savedSample = sampleService.save(sample);
        return ApiResponseUtil.setSuccessResponse(getMessage("sample.created.success"), savedSample);
    }

    /**
     * PUT /api/v1/sample/{id} - Update existing sample
     * OpenAPI: Update an existing sample by ID
     */
    @PutMapping("/sample/{id}")
    public ResponseEntity<ApiResponse> updateSample(
            @PathVariable Long id, 
            @RequestBody Sample sample,
            @RequestHeader(name = CommonConstants.CustomHeaders.X_TENANT_ID) String tenantId,
            @RequestHeader(name = CommonConstants.CustomHeaders.X_USER_ID) String userId,
            @RequestHeader(name = CommonConstants.CustomHeaders.X_AUTH_TOKEN) String authorization) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException(getMessage("sample.invalid.id"));
        }
        
        if (sample == null) {
            throw new IllegalArgumentException(getMessage("sample.data.null"));
        }
        
        if (sample.getName() == null || sample.getName().trim().isEmpty()) {
            throw new IllegalArgumentException(getMessage("sample.name.required"));
        }
        
        // Check if sample exists
        if (!sampleService.existsById(id)) {
            throw new IllegalArgumentException(getMessage("sample.not.found", id));
        }
        
        // Check if name conflict with other samples
        var existingSamples = sampleService.findByName(sample.getName());
        if (existingSamples != null && !existingSamples.isEmpty()) {
            Sample existingSample = existingSamples.get(0);
            if (!existingSample.getId().equals(id)) {
                throw new IllegalStateException(getMessage("sample.duplicate.name", sample.getName()));
            }
        }
        
        sample.setId(id);
        Sample updatedSample = sampleService.update(sample);
        return ApiResponseUtil.setSuccessResponse(getMessage("sample.updated.success"), updatedSample);
    }

        /**
     * DELETE /api/v1/sample/{id} - Delete sample by ID
     * OpenAPI: Delete a specific sample by its ID
     */
    @DeleteMapping("/sample/{id}")
    public ResponseEntity<ApiResponse> deleteSample(
            @PathVariable Long id,
            @RequestHeader(name = CommonConstants.CustomHeaders.X_TENANT_ID) String tenantId,
            @RequestHeader(name = CommonConstants.CustomHeaders.X_USER_ID) String userId,
            @RequestHeader(name = CommonConstants.CustomHeaders.X_AUTH_TOKEN) String authorization) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException(getMessage("sample.invalid.id"));
        }
        
        // Check if sample exists
        if (!sampleService.existsById(id)) {
            throw new IllegalArgumentException(getMessage("sample.not.found", id));
        }
        
        sampleService.deleteById(id);
        return ApiResponseUtil.setSuccessResponse(getMessage("sample.deleted.success"), null);
    }
}
