package com.example.service;

import com.astreya.multisource.dbstarter.config.DbExecutor;
import com.astreya.multisource.dbstarter.util.JpaQueryHelper;
import com.example.entity.Sample;
import com.example.query.SampleQuery;
import com.example.util.MapperUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class SampleServiceImpl implements SampleService {

    @Autowired
    DbExecutor dbExecutor;

    @Autowired
    JpaQueryHelper jpaQueryHelper;

    // Basic CRUD operations
    @Override
    @Transactional(readOnly = true)
    public List<List<Sample>> findAll() {
        return jpaQueryHelper.findAll(Sample.class);
    }

    public void listAll() {
        
        jpaQueryHelper.findAll(Sample.class);
        jpaQueryHelper.findById(Sample.class, 1L);
        jpaQueryHelper.findByField(Sample.class, "name", "test");
        jpaQueryHelper.insert(Sample.class, Map.of("name", "test"));
        jpaQueryHelper.updateByField(Sample.class, Map.of("id", 1L, "name", "test"),"name", "test");
        jpaQueryHelper.deleteByField(Sample.class, "name", "test");     
    }
    @Override
    @Transactional(readOnly = true)
    public List<Sample> findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        List<List<Sample>> results = jpaQueryHelper.findById(Sample.class, id);
        // Flatten results from all tenants
        return results.stream()
            .flatMap(List::stream)
            .toList();
    }

    @Override
    public Sample save(Sample sample) {
        if (sample == null) {
            throw new IllegalArgumentException("Sample cannot be null");
        }
        if (sample.getName() == null || sample.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Sample name cannot be null or empty");
        }
        
        // Set audit fields if not already set
        if (sample.getCreatedOn() == null) {
            sample.setCreatedOn(LocalDateTime.now());
        }
        if (sample.getStatus() == null) {
            sample.setStatus("INACTIVE");
        }
        
        // Use MapperUtil to create parameters for save operation
        Map<String, Object> parameters = MapperUtil.mapSampleToSaveParameters(sample);
        
        // Execute the save operation using JpaQueryHelper
        List<List<Integer>> results = jpaQueryHelper.insert(Sample.class, parameters);
        
        // Check if the insert was successful (results should contain the generated ID)
        if (results != null && !results.isEmpty() && !results.get(0).isEmpty()) {
            Integer generatedId = results.get(0).get(0);
            if (generatedId != null) {
                sample.setId(generatedId.longValue());
            }
        }
        
        return sample;
    }

    @Override
    public Sample update(Sample sample) {
        if (sample == null) {
            throw new IllegalArgumentException("Sample cannot be null");
        }
        if (sample.getId() == null) {
            throw new IllegalArgumentException("Sample ID cannot be null for update");
        }
        if (sample.getName() == null || sample.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Sample name cannot be null or empty");
        }
        
        // Check if sample exists using our service method
        if (!existsById(sample.getId())) {
            throw new IllegalArgumentException(String.format("Sample with ID %d does not exist", sample.getId()));
        }
        
        // Set modification timestamp
        sample.setModifiedOn(LocalDateTime.now());
        
        // Use MapperUtil to create parameters for update operation
        Map<String, Object> parameters = MapperUtil.mapSampleToUpdateParameters(sample);
        
        // Execute the update operation using JpaQueryHelper
        List<List<Integer>> results = jpaQueryHelper.updateByField(Sample.class, parameters, "id", sample.getId());
        
        // Check if the update was successful (results should contain the number of affected rows)
        if (results != null && !results.isEmpty() && !results.get(0).isEmpty()) {
            Integer affectedRows = results.get(0).get(0);
            if (affectedRows == null || affectedRows == 0) {
                throw new IllegalStateException("Update operation failed - no rows were affected");
            }
        }
        
        return sample;
    }

    @Override
    public void deleteById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        if (!existsById(id)) {
            throw new IllegalArgumentException(String.format("Sample with ID %d does not exist", id));
        }
        
        // Execute the delete operation using JpaQueryHelper
        jpaQueryHelper.deleteByField(Sample.class, "id", id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        if (id == null) {
            return false;
        }
        Map<String, Object> parameters = MapperUtil.mapSampleToExistsByIdParameters(id);
        List<List<Boolean>> results = dbExecutor.executeForAllTenants(SampleQuery.EXISTS_BY_ID, Boolean.class, parameters);
        // Flatten results from all tenants and return true if any tenant has the record
        return results.stream()
            .flatMap(List::stream)
            .anyMatch(exists -> exists != null && exists);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return false;
        }
        Map<String, Object> parameters = MapperUtil.mapSampleToExistsByNameParameters(name);
        List<List<Boolean>> results = dbExecutor.executeForAllTenants(SampleQuery.EXISTS_BY_NAME, Boolean.class, parameters);
        // Flatten results from all tenants and return true if any tenant has the record
        return results.stream()
            .flatMap(List::stream)
            .anyMatch(exists -> exists != null && exists);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Sample> findByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        List<List<Sample>> results = jpaQueryHelper.findByField(Sample.class, "name", name);
        // Flatten results from all tenants
        return results.stream()
            .flatMap(List::stream)
            .toList();
    }
}
