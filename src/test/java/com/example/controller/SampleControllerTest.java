package com.example.controller;

import com.astreya.core.starter.dto.ApiResponse;
import com.example.entity.Sample;
import com.example.service.SampleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SampleControllerTest {

    @Mock
    private SampleService sampleService;

    @Mock
    private MessageSource messageSource;

    @InjectMocks
    private SampleController sampleController;

    private Sample sample1;
    private Sample sample2;
    private List<List<Sample>> sampleLists;

    @BeforeEach
    void setUp() {
        // Create test data
        sample1 = new Sample();
        sample1.setId(1L);
        sample1.setName("Test Sample 1");
        sample1.setStatus("ACTIVE");
        sample1.setCreatedBy("test-user");
        sample1.setCreatedOn(LocalDateTime.now());
        sample1.setModifiedBy("test-user");
        sample1.setModifiedOn(LocalDateTime.now());

        sample2 = new Sample();
        sample2.setId(2L);
        sample2.setName("Test Sample 2");
        sample2.setStatus("INACTIVE");
        sample2.setCreatedBy("test-user");
        sample2.setCreatedOn(LocalDateTime.now());
        sample2.setModifiedBy("test-user");
        sample2.setModifiedOn(LocalDateTime.now());

        sampleLists = Arrays.asList(Arrays.asList(sample1), Arrays.asList(sample2));

        // Setup default message source behavior
        when(messageSource.getMessage(anyString(), any(), anyString(), any()))
            .thenAnswer(invocation -> invocation.getArgument(0));
    }

    // Test Method 1: getAllSamples
    @Test
    void getAllSamples_ShouldReturnAllSamples() {
        // Given
        when(sampleService.findAll()).thenReturn(sampleLists);

        // When
        ResponseEntity<ApiResponse> response = sampleController.getAllSamples("tenant1", "user1", "token1");

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isSuccess());
        assertNotNull(response.getBody().getData());

        verify(sampleService).findAll();
    }

    @Test
    void getAllSamples_WhenServiceThrowsException_ShouldReturnErrorResponse() {
        // Given
        when(sampleService.findAll()).thenThrow(new RuntimeException("Database error"));

        // When
        ResponseEntity<ApiResponse> response = sampleController.getAllSamples("tenant1", "user1", "token1");

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNotNull(response.getBody());
        assertFalse(response.getBody().isSuccess());

        verify(sampleService).findAll();
    }

    // Test Method 2: getSampleById
    @Test
    void getSampleById_WithValidId_ShouldReturnSample() {
        // Given
        when(sampleService.findById(1L)).thenReturn(Arrays.asList(sample1));

        // When
        ResponseEntity<ApiResponse> response = sampleController.getSampleById(1L, "tenant1", "user1", "token1");

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isSuccess());
        assertNotNull(response.getBody().getData());

        verify(sampleService).findById(1L);
    }

    @Test
    void getSampleById_WithNullId_ShouldThrowException() {
        // Given & When & Then
        assertThrows(IllegalArgumentException.class, () -> 
            sampleController.getSampleById(null, "tenant1", "user1", "token1"));
        
        verify(sampleService, never()).findById(any());
    }

    @Test
    void getSampleById_WithZeroId_ShouldThrowException() {
        // Given & When & Then
        assertThrows(IllegalArgumentException.class, () -> 
            sampleController.getSampleById(0L, "tenant1", "user1", "token1"));
        
        verify(sampleService, never()).findById(any());
    }

    @Test
    void getSampleById_WithNegativeId_ShouldThrowException() {
        // Given & When & Then
        assertThrows(IllegalArgumentException.class, () -> 
            sampleController.getSampleById(-1L, "tenant1", "user1", "token1"));
        
        verify(sampleService, never()).findById(any());
    }

    @Test
    void getSampleById_WithNonExistingId_ShouldThrowException() {
        // Given
        when(sampleService.findById(999L)).thenReturn(Arrays.asList());

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> 
            sampleController.getSampleById(999L, "tenant1", "user1", "token1"));

        verify(sampleService).findById(999L);
    }

    // Test Method 3: createSample
    @Test
    void createSample_WithValidSample_ShouldReturnCreatedSample() {
        // Given
        Sample newSample = new Sample();
        newSample.setName("New Sample");
        newSample.setCreatedBy("test-user");

        when(sampleService.existsByName("New Sample")).thenReturn(false);
        when(sampleService.save(any(Sample.class))).thenReturn(sample1);

        // When
        ResponseEntity<ApiResponse> response = sampleController.createSample(newSample, "tenant1", "user1", "token1");

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isSuccess());
        assertNotNull(response.getBody().getData());

        verify(sampleService).existsByName("New Sample");
        verify(sampleService).save(newSample);
    }

    @Test
    void createSample_WithNullSample_ShouldThrowException() {
        // Given & When & Then
        assertThrows(IllegalArgumentException.class, () -> 
            sampleController.createSample(null, "tenant1", "user1", "token1"));
        
        verify(sampleService, never()).existsByName(any());
        verify(sampleService, never()).save(any());
    }

    @Test
    void createSample_WithNullName_ShouldThrowException() {
        // Given
        Sample newSample = new Sample();
        newSample.setName(null);
        newSample.setCreatedBy("test-user");

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> 
            sampleController.createSample(newSample, "tenant1", "user1", "token1"));
        
        verify(sampleService, never()).existsByName(any());
        verify(sampleService, never()).save(any());
    }

    @Test
    void createSample_WithEmptyName_ShouldThrowException() {
        // Given
        Sample newSample = new Sample();
        newSample.setName("   ");
        newSample.setCreatedBy("test-user");

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> 
            sampleController.createSample(newSample, "tenant1", "user1", "token1"));
        
        verify(sampleService, never()).existsByName(any());
        verify(sampleService, never()).save(any());
    }

    @Test
    void createSample_WithDuplicateName_ShouldThrowException() {
        // Given
        Sample newSample = new Sample();
        newSample.setName("Existing Sample");
        newSample.setCreatedBy("test-user");

        when(sampleService.existsByName("Existing Sample")).thenReturn(true);

        // When & Then
        assertThrows(IllegalStateException.class, () -> 
            sampleController.createSample(newSample, "tenant1", "user1", "token1"));

        verify(sampleService).existsByName("Existing Sample");
        verify(sampleService, never()).save(any());
    }

    // Test Method 4: updateSample
    @Test
    void updateSample_WithValidSample_ShouldReturnUpdatedSample() {
        // Given
        Sample updateSample = new Sample();
        updateSample.setName("Updated Sample");
        updateSample.setModifiedBy("test-user");

        when(sampleService.existsById(1L)).thenReturn(true);
        when(sampleService.findByName("Updated Sample")).thenReturn(Arrays.asList());
        when(sampleService.update(any(Sample.class))).thenReturn(sample1);

        // When
        ResponseEntity<ApiResponse> response = sampleController.updateSample(1L, updateSample, "tenant1", "user1", "token1");

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isSuccess());
        assertNotNull(response.getBody().getData());
        assertEquals(1L, updateSample.getId()); // Verify ID was set

        verify(sampleService).existsById(1L);
        verify(sampleService).findByName("Updated Sample");
        verify(sampleService).update(updateSample);
    }

    @Test
    void updateSample_WithNullId_ShouldThrowException() {
        // Given
        Sample updateSample = new Sample();
        updateSample.setName("Updated Sample");

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> 
            sampleController.updateSample(null, updateSample, "tenant1", "user1", "token1"));
        
        verify(sampleService, never()).existsById(any());
        verify(sampleService, never()).findByName(any());
        verify(sampleService, never()).update(any());
    }

    @Test
    void updateSample_WithZeroId_ShouldThrowException() {
        // Given
        Sample updateSample = new Sample();
        updateSample.setName("Updated Sample");

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> 
            sampleController.updateSample(0L, updateSample, "tenant1", "user1", "token1"));
        
        verify(sampleService, never()).existsById(any());
        verify(sampleService, never()).findByName(any());
        verify(sampleService, never()).update(any());
    }

    @Test
    void updateSample_WithNullSample_ShouldThrowException() {
        // Given & When & Then
        assertThrows(IllegalArgumentException.class, () -> 
            sampleController.updateSample(1L, null, "tenant1", "user1", "token1"));
        
        verify(sampleService, never()).existsById(any());
        verify(sampleService, never()).findByName(any());
        verify(sampleService, never()).update(any());
    }

    @Test
    void updateSample_WithNullName_ShouldThrowException() {
        // Given
        Sample updateSample = new Sample();
        updateSample.setName(null);

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> 
            sampleController.updateSample(1L, updateSample, "tenant1", "user1", "token1"));
        
        verify(sampleService, never()).existsById(any());
        verify(sampleService, never()).findByName(any());
        verify(sampleService, never()).update(any());
    }

    @Test
    void updateSample_WithNonExistingId_ShouldThrowException() {
        // Given
        Sample updateSample = new Sample();
        updateSample.setName("Updated Sample");

        when(sampleService.existsById(999L)).thenReturn(false);

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> 
            sampleController.updateSample(999L, updateSample, "tenant1", "user1", "token1"));

        verify(sampleService).existsById(999L);
        verify(sampleService, never()).findByName(any());
        verify(sampleService, never()).update(any());
    }

    @Test
    void updateSample_WithNameConflict_ShouldThrowException() {
        // Given
        Sample updateSample = new Sample();
        updateSample.setName("Existing Name");

        Sample existingSample = new Sample();
        existingSample.setId(2L);
        existingSample.setName("Existing Name");

        when(sampleService.existsById(1L)).thenReturn(true);
        when(sampleService.findByName("Existing Name")).thenReturn(Arrays.asList(existingSample));

        // When & Then
        assertThrows(IllegalStateException.class, () -> 
            sampleController.updateSample(1L, updateSample, "tenant1", "user1", "token1"));

        verify(sampleService).existsById(1L);
        verify(sampleService).findByName("Existing Name");
        verify(sampleService, never()).update(any());
    }

    // Test Method 5: deleteSample
    @Test
    void deleteSample_WithValidId_ShouldReturnSuccessResponse() {
        // Given
        when(sampleService.existsById(1L)).thenReturn(true);
        doNothing().when(sampleService).deleteById(1L);

        // When
        ResponseEntity<ApiResponse> response = sampleController.deleteSample(1L, "tenant1", "user1", "token1");

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isSuccess());

        verify(sampleService).existsById(1L);
        verify(sampleService).deleteById(1L);
    }

    @Test
    void deleteSample_WithNullId_ShouldThrowException() {
        // Given & When & Then
        assertThrows(IllegalArgumentException.class, () -> 
            sampleController.deleteSample(null, "tenant1", "user1", "token1"));
        
        verify(sampleService, never()).existsById(any());
        verify(sampleService, never()).deleteById(any());
    }

    @Test
    void deleteSample_WithZeroId_ShouldThrowException() {
        // Given & When & Then
        assertThrows(IllegalArgumentException.class, () -> 
            sampleController.deleteSample(0L, "tenant1", "user1", "token1"));
        
        verify(sampleService, never()).existsById(any());
        verify(sampleService, never()).deleteById(any());
    }

    @Test
    void deleteSample_WithNonExistingId_ShouldThrowException() {
        // Given
        when(sampleService.existsById(999L)).thenReturn(false);

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> 
            sampleController.deleteSample(999L, "tenant1", "user1", "token1"));

        verify(sampleService).existsById(999L);
        verify(sampleService, never()).deleteById(any());
    }
}
