package com.example.constants;

/**
 * Common constants used throughout the application
 * Contains entity column names, default values, and custom headers
 */
public class CommonConstants {

    // Sample Entity Database Constants
    public static final class SampleEntity {
        // Table Name
        public static final String TABLE_NAME = "sample";

        // Column Names
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_CREATED_BY = "created_by";
        public static final String COLUMN_CREATED_ON = "created_on";
        public static final String COLUMN_MODIFIED_BY = "modified_by";
        public static final String COLUMN_MODIFIED_ON = "modified_on";
        public static final String COLUMN_STATUS = "status";

        // Column Lengths (matching entity annotations)
        public static final int NAME_MAX_LENGTH = 255;
        public static final int CREATED_BY_MAX_LENGTH = 100;
        public static final int MODIFIED_BY_MAX_LENGTH = 100;

        // Default Values
        public static final String DEFAULT_STATUS = "ACTIVE";
        public static final String DEFAULT_CREATED_BY = "system";
        
        // Validation Messages
        public static final String NAME_SIZE_MESSAGE = "Name must be between 2 and 255 characters";
        public static final String CREATED_BY_SIZE_MESSAGE = "Created by must not exceed 100 characters";
        public static final String MODIFIED_BY_SIZE_MESSAGE = "Modified by must not exceed 100 characters";
    }

    // Custom Headers (if needed beyond standard HTTP headers)
    public static final class CustomHeaders {
        public static final String X_TENANT_ID = "X-Tenant-ID";
        public static final String X_USER_ID = "X-User-ID";
        public static final String X_AUTH_TOKEN = "Auth_Token";
        
    }

    // Private constructor to prevent instantiation
    private CommonConstants() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }
}
