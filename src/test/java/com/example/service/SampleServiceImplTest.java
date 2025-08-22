package com.example.service;

import com.astreya.multisource.dbstarter.util.JpaQueryHelper;
import com.astreya.multisource.dbstarter.config.DbExecutor;
import com.example.entity.Sample;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import com.example.query.SampleQuery;

@ExtendWith(MockitoExtension.class)
class SampleServiceImplTest {

    @Mock
    private JpaQueryHelper jpaQueryHelper;

    @Mock
    private DbExecutor dbExecutor;

    @InjectMocks
    private SampleServiceImpl sampleService;

    // Helper method to create test data only when needed
    private List<List<Sample>> createTestSampleList() {
        Sample testSample1 = new Sample();
        testSample1.setId(1L);
        testSample1.setName("Test Sample 1");
        testSample1.setStatus("ACTIVE");

        Sample testSample2 = new Sample();
        testSample2.setId(2L);
        testSample2.setName("Test Sample 2");
        testSample2.setStatus("INACTIVE");

        return Arrays.asList(Arrays.asList(testSample1), Arrays.asList(testSample2));
    }

    // Test Method 1: findAll
    @Test
    void findAll_ShouldReturnAllSamples() {
        // Given
        List<List<Sample>> expectedFindAllResult = createTestSampleList();
        when(jpaQueryHelper.findAll(Sample.class)).thenReturn(expectedFindAllResult);

        // When
        List<List<Sample>> result = sampleService.findAll();

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).size());
        assertEquals(1, result.get(1).size());
        assertEquals(expectedFindAllResult.get(0).get(0), result.get(0).get(0));
        assertEquals(expectedFindAllResult.get(1).get(0), result.get(1).get(0));

        verify(jpaQueryHelper).findAll(Sample.class);
    }

    @Test
    void findAll_WhenNoSamplesExist_ShouldReturnEmptyList() {
        // Given
        when(jpaQueryHelper.findAll(Sample.class)).thenReturn(Arrays.asList());

        // When
        List<List<Sample>> result = sampleService.findAll();

        // Then
        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(jpaQueryHelper).findAll(Sample.class);
    }

    @Test
    void findAll_WhenJpaQueryHelperThrowsException_ShouldPropagateException() {
        // Given
        when(jpaQueryHelper.findAll(Sample.class)).thenThrow(new RuntimeException("Database error"));

        // When & Then
        assertThrows(RuntimeException.class, () -> sampleService.findAll());
        verify(jpaQueryHelper).findAll(Sample.class);
    }

    // Test Method 2: save
    @Test
    void save_WithValidSample_ShouldSaveSample() {
        // Given
        Sample newSample = new Sample();
        newSample.setName("New Sample");
        newSample.setCreatedBy("testUser");
        
        List<List<Integer>> mockInsertResults = Arrays.asList(Arrays.asList(1));
        when(jpaQueryHelper.insert(eq(Sample.class), anyMap())).thenReturn(mockInsertResults);

        // When
        Sample result = sampleService.save(newSample);

        // Then
        assertNotNull(result);
        assertEquals("New Sample", result.getName());
        assertEquals("testUser", result.getCreatedBy());
        assertEquals("INACTIVE", result.getStatus()); // Default status
        assertNotNull(result.getCreatedOn()); // Should be set automatically
        assertEquals(1L, result.getId()); // Generated ID should be set

        verify(jpaQueryHelper).insert(eq(Sample.class), anyMap());
    }

    // Test Method 3: update
    @Test
    void update_WithValidSample_ShouldUpdateSample() {
        // Given
        Sample updateSample = new Sample();
        updateSample.setId(1L);
        updateSample.setName("Updated Sample");
        updateSample.setModifiedBy("testUser");
        
        // Mock existsById to return true (sample exists)
        when(dbExecutor.executeForAllTenants(eq(SampleQuery.EXISTS_BY_ID), eq(Boolean.class), anyMap()))
            .thenReturn(Arrays.asList(Arrays.asList(true)));
        
        List<List<Integer>> mockUpdateResults = Arrays.asList(Arrays.asList(1));
        when(jpaQueryHelper.updateByField(eq(Sample.class), anyMap(), eq("id"), eq(1L))).thenReturn(mockUpdateResults);

        // When
        Sample result = sampleService.update(updateSample);

        // Then
        assertNotNull(result);
        assertEquals("Updated Sample", result.getName());
        assertEquals("testUser", result.getModifiedBy());
        assertEquals(1L, result.getId());
        assertNotNull(result.getModifiedOn()); // Should be set automatically

        verify(dbExecutor).executeForAllTenants(eq(SampleQuery.EXISTS_BY_ID), eq(Boolean.class), anyMap());
        verify(jpaQueryHelper).updateByField(eq(Sample.class), anyMap(), eq("id"), eq(1L));
    }

    // Test Method 4: findById
    @Test
    void findById_WithValidId_ShouldReturnSamples() {
        // Given
        Sample sample1 = new Sample();
        sample1.setId(1L);
        sample1.setName("Test Sample 1");
        sample1.setStatus("ACTIVE");
        
        List<List<Sample>> mockResults = Arrays.asList(Arrays.asList(sample1));
        when(jpaQueryHelper.findById(eq(Sample.class), eq(1L))).thenReturn(mockResults);

        // When
        List<Sample> result = sampleService.findById(1L);

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(sample1, result.get(0));
        assertEquals(1L, result.get(0).getId());
        assertEquals("Test Sample 1", result.get(0).getName());
        assertEquals("ACTIVE", result.get(0).getStatus());

        verify(jpaQueryHelper).findById(eq(Sample.class), eq(1L));
    }

    // Test Method 5: deleteById
    @Test
    void deleteById_WithValidId_ShouldDeleteSample() {
        // Given
        Long sampleId = 1L;
        
        // Mock existsById to return true (sample exists)
        when(dbExecutor.executeForAllTenants(eq(SampleQuery.EXISTS_BY_ID), eq(Boolean.class), anyMap()))
            .thenReturn(Arrays.asList(Arrays.asList(true)));
        
        // Mock deleteByField to return affected rows
        when(jpaQueryHelper.deleteByField(eq(Sample.class), eq("id"), eq(sampleId)))
            .thenReturn(Arrays.asList(Arrays.asList(1)));

        // When
        sampleService.deleteById(sampleId);

        // Then
        verify(dbExecutor).executeForAllTenants(eq(SampleQuery.EXISTS_BY_ID), eq(Boolean.class), anyMap());
        verify(jpaQueryHelper).deleteByField(eq(Sample.class), eq("id"), eq(sampleId));
    }

    // Test Method 6: existsById
    @Test
    void existsById_WithValidId_ShouldReturnTrue() {
        // Given
        Long sampleId = 1L;
        when(dbExecutor.executeForAllTenants(eq(SampleQuery.EXISTS_BY_ID), eq(Boolean.class), anyMap()))
            .thenReturn(Arrays.asList(Arrays.asList(true)));

        // When
        boolean result = sampleService.existsById(sampleId);

        // Then
        assertTrue(result);
        verify(dbExecutor).executeForAllTenants(eq(SampleQuery.EXISTS_BY_ID), eq(Boolean.class), anyMap());
    }

    // Test Method 7: findByName
    @Test
    void findByName_WithValidName_ShouldReturnSamples() {
        // Given
        String sampleName = "Test Sample";
        Sample sample1 = new Sample();
        sample1.setId(1L);
        sample1.setName(sampleName);
        sample1.setStatus("ACTIVE");
        
        List<List<Sample>> mockResults = Arrays.asList(Arrays.asList(sample1));
        when(jpaQueryHelper.findByField(eq(Sample.class), eq("name"), eq(sampleName))).thenReturn(mockResults);

        // When
        List<Sample> result = sampleService.findByName(sampleName);

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(sample1, result.get(0));
        assertEquals(sampleName, result.get(0).getName());

        verify(jpaQueryHelper).findByField(eq(Sample.class), eq("name"), eq(sampleName));
    }

    // Test Method 8: existsByName
    @Test
    void existsByName_WithValidName_ShouldReturnTrue() {
        // Given
        String sampleName = "Test Sample";
        when(dbExecutor.executeForAllTenants(eq(SampleQuery.EXISTS_BY_NAME), eq(Boolean.class), anyMap()))
            .thenReturn(Arrays.asList(Arrays.asList(true)));

        // When
        boolean result = sampleService.existsByName(sampleName);

        // Then
        assertTrue(result);
        verify(dbExecutor).executeForAllTenants(eq(SampleQuery.EXISTS_BY_NAME), eq(Boolean.class), anyMap());
    }
}
