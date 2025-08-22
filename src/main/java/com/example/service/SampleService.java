package com.example.service;

import com.example.entity.Sample;

import java.util.List;

public interface SampleService {

    // Basic CRUD operations
    List<List<Sample>> findAll();
    List<Sample> findById(Long id);
    Sample save(Sample sample);
    Sample update(Sample sample);
    void deleteById(Long id);
    boolean existsById(Long id);
    
    // Custom repository methods
    List<Sample> findByName(String name);
    boolean existsByName(String name);
}
