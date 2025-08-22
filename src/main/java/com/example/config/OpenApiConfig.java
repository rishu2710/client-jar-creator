package com.example.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.List;

@Configuration
public class OpenApiConfig {
    
    @Value("${openapi.title}")
    private String title;
    
    @Value("${openapi.description}")
    private String description;
    
    @Value("${openapi.version}")
    private String version;
    
    @Value("${openapi.url}")
    private String url;
    
    @Value("${openapi.contact.name:Core Starter Team}")
    private String contactName;
    
    @Value("${openapi.contact.email:core-starter@astreya.com}")
    private String contactEmail;
    
    @Bean
    @Primary
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title(title)
                        .description(description)
                        .version(version)
                        .contact(new Contact()
                                .name(contactName)
                                .email(contactEmail))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")))
                .servers(List.of(
                        new Server()
                                .url(url)
                                .description("API Server")
                ));
    }
}
