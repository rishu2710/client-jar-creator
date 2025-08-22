package com.example.constants;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommonConstantsTest {

    @Test
    void testSampleEntityConstants_ShouldBeProperlyDefined() {
        // Table Name
        assertEquals("sample", CommonConstants.SampleEntity.TABLE_NAME);

        // Column Names
        assertEquals("id", CommonConstants.SampleEntity.COLUMN_ID);
        assertEquals("name", CommonConstants.SampleEntity.COLUMN_NAME);
        assertEquals("created_by", CommonConstants.SampleEntity.COLUMN_CREATED_BY);
        assertEquals("created_on", CommonConstants.SampleEntity.COLUMN_CREATED_ON);
        assertEquals("modified_by", CommonConstants.SampleEntity.COLUMN_MODIFIED_BY);
        assertEquals("modified_on", CommonConstants.SampleEntity.COLUMN_MODIFIED_ON);
        assertEquals("status", CommonConstants.SampleEntity.COLUMN_STATUS);

        // Column Lengths
        assertEquals(255, CommonConstants.SampleEntity.NAME_MAX_LENGTH);
        assertEquals(100, CommonConstants.SampleEntity.CREATED_BY_MAX_LENGTH);
        assertEquals(100, CommonConstants.SampleEntity.MODIFIED_BY_MAX_LENGTH);

        // Default Values
        assertEquals("ACTIVE", CommonConstants.SampleEntity.DEFAULT_STATUS);
        assertEquals("system", CommonConstants.SampleEntity.DEFAULT_CREATED_BY);

        // Validation Messages
        assertEquals("Name must be between 2 and 255 characters", 
            CommonConstants.SampleEntity.NAME_SIZE_MESSAGE);
        assertEquals("Created by must not exceed 100 characters", 
            CommonConstants.SampleEntity.CREATED_BY_SIZE_MESSAGE);
        assertEquals("Modified by must not exceed 100 characters", 
            CommonConstants.SampleEntity.MODIFIED_BY_SIZE_MESSAGE);
    }

    @Test
    void testCustomHeadersConstants_ShouldBeProperlyDefined() {
        assertEquals("X-Tenant-ID", CommonConstants.CustomHeaders.X_TENANT_ID);
        assertEquals("X-User-ID", CommonConstants.CustomHeaders.X_USER_ID);
        assertEquals("Auth_Token", CommonConstants.CustomHeaders.X_AUTH_TOKEN);
    }

    @Test
    void testSampleEntityConstants_ShouldNotBeNull() {
        // Verify all constants are not null
        assertNotNull(CommonConstants.SampleEntity.TABLE_NAME);
        assertNotNull(CommonConstants.SampleEntity.COLUMN_ID);
        assertNotNull(CommonConstants.SampleEntity.COLUMN_NAME);
        assertNotNull(CommonConstants.SampleEntity.COLUMN_CREATED_BY);
        assertNotNull(CommonConstants.SampleEntity.COLUMN_CREATED_ON);
        assertNotNull(CommonConstants.SampleEntity.COLUMN_MODIFIED_BY);
        assertNotNull(CommonConstants.SampleEntity.COLUMN_MODIFIED_ON);
        assertNotNull(CommonConstants.SampleEntity.COLUMN_STATUS);
        assertNotNull(CommonConstants.SampleEntity.NAME_MAX_LENGTH);
        assertNotNull(CommonConstants.SampleEntity.CREATED_BY_MAX_LENGTH);
        assertNotNull(CommonConstants.SampleEntity.MODIFIED_BY_MAX_LENGTH);
        assertNotNull(CommonConstants.SampleEntity.DEFAULT_STATUS);
        assertNotNull(CommonConstants.SampleEntity.DEFAULT_CREATED_BY);
        assertNotNull(CommonConstants.SampleEntity.NAME_SIZE_MESSAGE);
        assertNotNull(CommonConstants.SampleEntity.CREATED_BY_SIZE_MESSAGE);
        assertNotNull(CommonConstants.SampleEntity.MODIFIED_BY_SIZE_MESSAGE);
    }

    @Test
    void testCustomHeadersConstants_ShouldNotBeNull() {
        assertNotNull(CommonConstants.CustomHeaders.X_TENANT_ID);
        assertNotNull(CommonConstants.CustomHeaders.X_USER_ID);
        assertNotNull(CommonConstants.CustomHeaders.X_AUTH_TOKEN);
    }

    @Test
    void testSampleEntityConstants_ShouldHaveValidValues() {
        // Verify numeric values are positive
        assertTrue(CommonConstants.SampleEntity.NAME_MAX_LENGTH > 0);
        assertTrue(CommonConstants.SampleEntity.CREATED_BY_MAX_LENGTH > 0);
        assertTrue(CommonConstants.SampleEntity.MODIFIED_BY_MAX_LENGTH > 0);

        // Verify string values are not empty
        assertFalse(CommonConstants.SampleEntity.TABLE_NAME.isEmpty());
        assertFalse(CommonConstants.SampleEntity.COLUMN_ID.isEmpty());
        assertFalse(CommonConstants.SampleEntity.COLUMN_NAME.isEmpty());
        assertFalse(CommonConstants.SampleEntity.COLUMN_CREATED_BY.isEmpty());
        assertFalse(CommonConstants.SampleEntity.COLUMN_CREATED_ON.isEmpty());
        assertFalse(CommonConstants.SampleEntity.COLUMN_MODIFIED_BY.isEmpty());
        assertFalse(CommonConstants.SampleEntity.COLUMN_MODIFIED_ON.isEmpty());
        assertFalse(CommonConstants.SampleEntity.COLUMN_STATUS.isEmpty());
        assertFalse(CommonConstants.SampleEntity.DEFAULT_STATUS.isEmpty());
        assertFalse(CommonConstants.SampleEntity.DEFAULT_CREATED_BY.isEmpty());
        assertFalse(CommonConstants.SampleEntity.NAME_SIZE_MESSAGE.isEmpty());
        assertFalse(CommonConstants.SampleEntity.CREATED_BY_SIZE_MESSAGE.isEmpty());
        assertFalse(CommonConstants.SampleEntity.MODIFIED_BY_SIZE_MESSAGE.isEmpty());
    }

    @Test
    void testCustomHeadersConstants_ShouldHaveValidValues() {
        assertFalse(CommonConstants.CustomHeaders.X_TENANT_ID.isEmpty());
        assertFalse(CommonConstants.CustomHeaders.X_USER_ID.isEmpty());
        assertFalse(CommonConstants.CustomHeaders.X_AUTH_TOKEN.isEmpty());
    }

    @Test
    void testSampleEntityConstants_ShouldHaveConsistentNaming() {
        // Verify column names follow consistent naming convention
        assertTrue(CommonConstants.SampleEntity.COLUMN_ID.matches("^[a-z_]+$"));
        assertTrue(CommonConstants.SampleEntity.COLUMN_NAME.matches("^[a-z_]+$"));
        assertTrue(CommonConstants.SampleEntity.COLUMN_CREATED_BY.matches("^[a-z_]+$"));
        assertTrue(CommonConstants.SampleEntity.COLUMN_CREATED_ON.matches("^[a-z_]+$"));
        assertTrue(CommonConstants.SampleEntity.COLUMN_MODIFIED_BY.matches("^[a-z_]+$"));
        assertTrue(CommonConstants.SampleEntity.COLUMN_MODIFIED_ON.matches("^[a-z_]+$"));
        assertTrue(CommonConstants.SampleEntity.COLUMN_STATUS.matches("^[a-z_]+$"));
    }

    @Test
    void testCustomHeadersConstants_ShouldFollowHeaderConvention() {
        // Verify custom headers follow HTTP header naming convention
        assertTrue(CommonConstants.CustomHeaders.X_TENANT_ID.matches("^[A-Z][a-zA-Z0-9-]*$"));
        assertTrue(CommonConstants.CustomHeaders.X_USER_ID.matches("^[A-Z][a-zA-Z0-9-]*$"));
        assertTrue(CommonConstants.CustomHeaders.X_AUTH_TOKEN.matches("^[A-Z][a-zA-Z0-9-_]*$"));
    }

    @Test
    void testSampleEntityConstants_ShouldHaveReasonableLengths() {
        // Verify column lengths are reasonable for database fields
        assertTrue(CommonConstants.SampleEntity.NAME_MAX_LENGTH <= 1000);
        assertTrue(CommonConstants.SampleEntity.CREATED_BY_MAX_LENGTH <= 255);
        assertTrue(CommonConstants.SampleEntity.MODIFIED_BY_MAX_LENGTH <= 255);
    }

    @Test
    void testSampleEntityConstants_ShouldHaveValidTableName() {
        // Verify table name is a valid SQL identifier
        assertTrue(CommonConstants.SampleEntity.TABLE_NAME.matches("^[a-z][a-z0-9_]*$"));
    }

    @Test
    void testSampleEntityConstants_ShouldHaveValidColumnNames() {
        // Verify all column names are valid SQL identifiers
        String[] columnNames = {
            CommonConstants.SampleEntity.COLUMN_ID,
            CommonConstants.SampleEntity.COLUMN_NAME,
            CommonConstants.SampleEntity.COLUMN_CREATED_BY,
            CommonConstants.SampleEntity.COLUMN_CREATED_ON,
            CommonConstants.SampleEntity.COLUMN_MODIFIED_BY,
            CommonConstants.SampleEntity.COLUMN_MODIFIED_ON,
            CommonConstants.SampleEntity.COLUMN_STATUS
        };

        for (String columnName : columnNames) {
            assertTrue(columnName.matches("^[a-z][a-z0-9_]*$"), 
                "Column name '" + columnName + "' should be a valid SQL identifier");
        }
    }

    @Test
    void testSampleEntityConstants_ShouldHaveConsistentMaxLengths() {
        // Verify that max lengths are consistent with typical database field sizes
        assertTrue(CommonConstants.SampleEntity.NAME_MAX_LENGTH >= 50, 
            "Name max length should be at least 50 for meaningful names");
        assertTrue(CommonConstants.SampleEntity.CREATED_BY_MAX_LENGTH >= 20, 
            "Created by max length should be at least 20 for user identifiers");
        assertTrue(CommonConstants.SampleEntity.MODIFIED_BY_MAX_LENGTH >= 20, 
            "Modified by max length should be at least 20 for user identifiers");
    }

    @Test
    void testSampleEntityConstants_ShouldHaveMeaningfulDefaultValues() {
        // Verify default values make sense
        assertFalse(CommonConstants.SampleEntity.DEFAULT_STATUS.isEmpty());
        assertFalse(CommonConstants.SampleEntity.DEFAULT_CREATED_BY.isEmpty());
        
        // Verify status is a valid status value
        assertTrue(CommonConstants.SampleEntity.DEFAULT_STATUS.equals("ACTIVE") || 
                  CommonConstants.SampleEntity.DEFAULT_STATUS.equals("INACTIVE"));
    }

    @Test
    void testSampleEntityConstants_ShouldHaveHelpfulValidationMessages() {
        // Verify validation messages are helpful and include the constraint information
        assertTrue(CommonConstants.SampleEntity.NAME_SIZE_MESSAGE.contains("2"));
        assertTrue(CommonConstants.SampleEntity.NAME_SIZE_MESSAGE.contains("255"));
        assertTrue(CommonConstants.SampleEntity.CREATED_BY_SIZE_MESSAGE.contains("100"));
        assertTrue(CommonConstants.SampleEntity.MODIFIED_BY_SIZE_MESSAGE.contains("100"));
    }
}
