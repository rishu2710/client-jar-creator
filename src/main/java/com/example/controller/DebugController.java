package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.models.OpenAPI;

@RestController
public class DebugController {
    
    @Autowired
    private OpenAPI openAPI;
    
    @GetMapping("/debug-openapi")
    public String debugOpenAPI() {
        // This should work since you can access the bean
        String title = openAPI.getInfo().getTitle();
        String version = openAPI.getInfo().getVersion();
        
        // Check what SpringDoc sees
        return String.format("Bean accessible - Title: %s, Version: %s", title, version);
    }
}
