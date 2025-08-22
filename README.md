# My Service

A Spring Boot service archetype


This is a Spring Boot service generated from the service-archetype template.


- Spring Boot 3.5.4
- Java 17
- Multi-source database support
- Core starter dependencies
- Comprehensive testing setup
- Global exception handling
- OpenAPI documentation



- Java 17 or higher
- Maven 3.6 or higher


```bash
mvn spring-boot:run
```


```bash
mvn test
```


```bash
mvn clean package
```


```
src/
├── main/
│   ├── java/
│   │   └── com.example/
│   │       ├── controller/
│   │       ├── service/
│   │       ├── entity/
│   │       ├── repository/
│   │       ├── exception/
│   │       ├── constants/
│   │       ├── query/
│   │       └── util/
│   └── resources/
│       ├── application.properties
│       └── messages*.properties
└── test/
    └── java/
        └── com.example/
            ├── controller/
            ├── service/
            └── exception/
```


The application can be configured through `application.properties` file.


Once the application is running, you can access the OpenAPI documentation at:
- Swagger UI: http://localhost:8080/swagger-ui.html
- OpenAPI JSON: http://localhost:8080/v3/api-docs


This project follows standard Spring Boot conventions and best practices.
