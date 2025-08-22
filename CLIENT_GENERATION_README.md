# Client JAR Generation Guide

This guide explains how to generate a client JAR from your API service for use in other projects.

## 🎯 **What This Does**

Generates a **Maven dependency JAR** that other projects can use to consume your API without writing HTTP client code from scratch.

## 📋 **Prerequisites**

- **Java 17** or higher
- **Maven** installed
- **Spring Boot app** running and accessible
- **OpenAPI specification** available at `/api-docs` endpoint

## 🚀 **Quick Start**

### **Step 1: Start Your Spring Boot App**
```bash
mvn spring-boot:run
```

### **Step 2: Verify API Documentation**
- Open: `http://localhost:8080/myservice-service/swagger-ui.html`
- Ensure Swagger UI loads without errors
- Verify `/api-docs` endpoint returns OpenAPI spec

### **Step 3: Generate Client JAR**
```bash
powershell -ExecutionPolicy Bypass -File generate-client.ps1
```

## 📁 **Generated Output**

After running the script, you'll get:
```
client-code/
├── src/                    # Generated Java source code
├── target/                 # Built JAR files
│   ├── openapi-java-client-1.0.0.jar          # Main JAR
│   ├── openapi-java-client-1.0.0-sources.jar  # Source JAR
│   └── openapi-java-client-1.0.0-tests.jar    # Test JAR
└── pom.xml                 # Maven project file
```

## 🔧 **Install to Local Maven Repository**

```bash
mvn install:install-file -Dfile="client-code/target/openapi-java-client-1.0.0.jar" -DgroupId="org.openapitools" -DartifactId="openapi-java-client" -Dversion="1.0.0" -Dpackaging="jar"
```

## 📦 **Using the Client in Other Projects**

### **Add Dependency to pom.xml:**
```xml
<dependency>
    <groupId>org.openapitools</groupId>
    <artifactId>openapi-java-client</artifactId>
    <version>1.0.0</version>
</dependency>
```

### **Configure Client:**
```java
@Configuration
public class ApiClientConfig {
    
    @Bean
    public ApiClient apiClient() {
        ApiClient client = new ApiClient();
        client.setBasePath("http://localhost:8080/myservice-service");
        return client;
    }
    
    @Bean
    public DefaultApi defaultApi(ApiClient apiClient) {
        return new DefaultApi(apiClient);
    }
}
```

### **Use the Client:**
```java
@Service
public class SampleService {
    
    @Autowired
    private DefaultApi apiClient;
    
    public List<Sample> getAllSamples() {
        try {
            return apiClient.getAllSamples();
        } catch (ApiException e) {
            throw new RuntimeException("Failed to fetch samples", e);
        }
    }
}
```

## ⚙️ **Configuration Options**

### **Library Options:**
- **`--library webclient`**: Modern, reactive HTTP client (recommended for Spring Boot 3.x)
- **`--library resttemplate`**: Traditional, blocking HTTP client

### **Additional Properties:**
- **`useJakartaEe=true`**: Ensures Spring Boot 3.x compatibility
- **Custom versions**: Override Spring, Jakarta, and other dependency versions

## 🔍 **Troubleshooting**

### **Swagger UI Not Loading:**
- Check if `/api-docs` endpoint returns 200 OK
- Verify OpenAPI configuration in your Spring Boot app
- Check for version conflicts in dependencies

### **Client Build Errors:**
- Ensure Java 17+ is being used
- Check Spring version compatibility
- Run `fix-pom.ps1` to update dependency versions

### **Version Conflicts:**
- The `fix-pom.ps1` script automatically updates Spring versions
- Ensures client uses Spring Boot 3.5.4 and Spring Framework 6.2.9
- Updates Jakarta EE versions for Spring Boot 3.x compatibility

## 📝 **Script Details**

### **generate-client.ps1:**
- Downloads OpenAPI specification
- Generates Java client code
- Applies POM fixes automatically
- Builds the client JAR

### **fix-pom.ps1:**
- Updates Spring Web version from 5.3.33 to 6.2.9
- Updates Jakarta annotation version from 1.3.5 to 2.1.6
- Updates Bean validation version from 2.0.2 to 3.0.2
- Updates Java compiler version from 1.8 to 17

## 🎯 **When to Use**

- **Building SDKs** for your API
- **Internal service communication** between microservices
- **Providing clients** to external teams
- **Creating integration libraries** for your services

## 🚫 **When NOT to Use**

- **Same project** that exposes the API
- **Simple internal tools** that don't need versioning
- **One-off scripts** that make direct HTTP calls

## 📚 **Additional Resources**

- [OpenAPI Generator Documentation](https://openapi-generator.tech/)
- [Spring WebClient Guide](https://docs.spring.io/spring-framework/reference/web/webflux-webclient.html)
- [Maven Install Plugin](https://maven.apache.org/plugins/maven-install-plugin/)

---

**Note**: This client generation is **optional** and only needed when you want to provide a client JAR for other projects to consume your API.
