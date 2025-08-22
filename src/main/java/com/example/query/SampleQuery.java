package com.example.query;

/**
 * JPQL queries equivalent to SampleRepository methods
 * These queries can be used for custom implementations or reference
 */
public class SampleQuery {

    // Entity name constant
    public static final String ENTITY_NAME = "Sample";

    // Field names (JPQL uses entity field names, not database column names)
    public static final String FIELD_ID = "id";
    public static final String FIELD_NAME = "name";
    public static final String FIELD_CREATED_BY = "createdBy";
    public static final String FIELD_CREATED_ON = "createdOn";
    public static final String FIELD_MODIFIED_BY = "modifiedBy";
    public static final String FIELD_MODIFIED_ON = "modifiedOn";
    public static final String FIELD_STATUS = "status";

    // Basic CRUD Queries
    public static final String FIND_ALL = 
        String.format("SELECT s FROM %s s ORDER BY s.%s", ENTITY_NAME, FIELD_ID);

    public static final String FIND_BY_ID = 
        String.format("SELECT s FROM %s s WHERE s.%s = :id", ENTITY_NAME, FIELD_ID);

    public static final String SAVE = 
        String.format("INSERT INTO %s (s.%s, s.%s, s.%s, s.%s, s.%s, s.%s) VALUES (?, ?, ?, ?, ?, ?)", 
            ENTITY_NAME, FIELD_NAME, FIELD_CREATED_BY, FIELD_CREATED_ON, FIELD_MODIFIED_BY, FIELD_MODIFIED_ON, FIELD_STATUS);

    public static final String UPDATE = 
        String.format("UPDATE %s s SET s.%s = :name, s.%s = :modifiedBy, s.%s = :modifiedOn, s.%s = :status WHERE s.%s = :id", 
            ENTITY_NAME, FIELD_NAME, FIELD_MODIFIED_BY, FIELD_MODIFIED_ON, FIELD_STATUS, FIELD_ID);

    public static final String DELETE_BY_ID = 
        String.format("DELETE FROM %s s WHERE s.%s = :id", ENTITY_NAME, FIELD_ID);

    public static final String EXISTS_BY_ID = 
        String.format("SELECT COUNT(s) > 0 FROM %s s WHERE s.%s = :id", ENTITY_NAME, FIELD_ID);

    // Custom Repository Method Queries (from SampleRepository)
    public static final String FIND_BY_NAME = 
        String.format("SELECT s FROM %s s WHERE s.%s = :name", ENTITY_NAME, FIELD_NAME);  // findByName()

    public static final String EXISTS_BY_NAME = 
        String.format("SELECT COUNT(s) > 0 FROM %s s WHERE s.%s = :name", ENTITY_NAME, FIELD_NAME);  // existsByName()

    // Count Queries
    public static final String COUNT_ALL = 
        String.format("SELECT COUNT(s) FROM %s s", ENTITY_NAME);

    public static final String COUNT_BY_STATUS = 
        String.format("SELECT COUNT(s) FROM %s s WHERE s.%s = :status", ENTITY_NAME, FIELD_STATUS);

    // Utility Queries
    public static final String FIND_NAMES_BY_STATUS = 
        String.format("SELECT s.%s FROM %s s WHERE s.%s = :status", FIELD_NAME, ENTITY_NAME, FIELD_STATUS);

    public static final String UPDATE_STATUS_BY_ID = 
        String.format("UPDATE %s s SET s.%s = :status, s.%s = :modifiedBy, s.%s = :modifiedOn WHERE s.%s = :id", 
            ENTITY_NAME, FIELD_STATUS, FIELD_MODIFIED_BY, FIELD_MODIFIED_ON, FIELD_ID);

    public static final String FIND_BY_CREATED_BY = 
        String.format("SELECT s FROM %s s WHERE s.%s = :createdBy", ENTITY_NAME, FIELD_CREATED_BY);

    public static final String FIND_BY_MODIFIED_BY = 
        String.format("SELECT s FROM %s s WHERE s.%s = :modifiedBy", ENTITY_NAME, FIELD_MODIFIED_BY);

    // Pagination Query
    public static final String FIND_ALL_WITH_PAGINATION = 
        String.format("SELECT s FROM %s s ORDER BY s.%s", ENTITY_NAME, FIELD_ID);

    // Search Query
    public static final String SEARCH_BY_NAME_OR_STATUS = 
        String.format("SELECT s FROM %s s WHERE s.%s LIKE :name OR s.%s = :status", ENTITY_NAME, FIELD_NAME, FIELD_STATUS);

    // Private constructor to prevent instantiation
    private SampleQuery() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }
}
