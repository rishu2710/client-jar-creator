package com.example.util;

import com.example.entity.Sample;

import java.util.HashMap;
import java.util.Map;

/**
 * Utility class for mapping entity properties to HashMap for JPQL queries
 */
public class MapperUtil {

    /**
     * Maps Sample entity properties to HashMap for SAVE query
     * @param sample the Sample entity to map
     * @return HashMap containing the mapped properties
     */
    public static Map<String, Object> mapSampleToSaveParameters(Sample sample) {
        Map<String, Object> parameters = new HashMap<>();
        
        if (sample != null) {
            parameters.put("name", sample.getName());
            parameters.put("createdBy", sample.getCreatedBy());
            parameters.put("createdOn", sample.getCreatedOn());
            parameters.put("modifiedBy", sample.getModifiedBy());
            parameters.put("modifiedOn", sample.getModifiedOn());
            parameters.put("status", sample.getStatus());
        }
        
        return parameters;
    }

    /**
     * Maps Sample entity properties to HashMap for UPDATE query
     * @param sample the Sample entity to map
     * @return HashMap containing the mapped properties
     */
    public static Map<String, Object> mapSampleToUpdateParameters(Sample sample) {
        Map<String, Object> parameters = new HashMap<>();
        
        if (sample != null) {
            parameters.put("id", sample.getId());
            parameters.put("name", sample.getName());
            parameters.put("modifiedBy", sample.getModifiedBy());
            parameters.put("modifiedOn", sample.getModifiedOn());
            parameters.put("status", sample.getStatus());
        }
        
        return parameters;
    }

    /**
     * Maps Sample entity properties to HashMap for DELETE query
     * @param id the ID to delete
     * @return HashMap containing the mapped properties
     */
    public static Map<String, Object> mapSampleToDeleteParameters(Long id) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", id);
        return parameters;
    }

    /**
     * Maps Sample entity properties to HashMap for FIND_BY_ID query
     * @param id the ID to find
     * @return HashMap containing the mapped properties
     */
    public static Map<String, Object> mapSampleToFindByIdParameters(Long id) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", id);
        return parameters;
    }

    /**
     * Maps Sample entity properties to HashMap for EXISTS_BY_ID query
     * @param id the ID to check existence
     * @return HashMap containing the mapped properties
     */
    public static Map<String, Object> mapSampleToExistsByIdParameters(Long id) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", id);
        return parameters;
    }

    /**
     * Maps Sample entity properties to HashMap for FIND_BY_NAME query
     * @param name the name to find
     * @return HashMap containing the mapped properties
     */
    public static Map<String, Object> mapSampleToFindByNameParameters(String name) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", name);
        return parameters;
    }

    /**
     * Maps Sample entity properties to HashMap for EXISTS_BY_NAME query
     * @param name the name to check existence
     * @return HashMap containing the mapped properties
     */
    public static Map<String, Object> mapSampleToExistsByNameParameters(String name) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", name);
        return parameters;
    }

    /**
     * Generic method to create a parameter map from key-value pairs
     * @param keyValuePairs alternating key and value pairs
     * @return HashMap containing the mapped properties
     */
    public static Map<String, Object> createParameterMap(Object... keyValuePairs) {
        Map<String, Object> parameters = new HashMap<>();
        
        if (keyValuePairs != null && keyValuePairs.length % 2 == 0) {
            for (int i = 0; i < keyValuePairs.length; i += 2) {
                if (keyValuePairs[i] instanceof String) {
                    String key = (String) keyValuePairs[i];
                    Object value = keyValuePairs[i + 1];
                    parameters.put(key, value);
                }
            }
        }
        
        return parameters;
    }
}
