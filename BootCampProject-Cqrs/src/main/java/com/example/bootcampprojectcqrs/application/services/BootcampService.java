package com.example.bootcampprojectcqrs.application.services;

import com.example.bootcampprojectcqrs.domain.entities.Bootcamp;

import java.util.List;

public interface BootcampService extends BaseService<Bootcamp, Long> {
    List<Bootcamp> getActiveBootcamps();
    List<Bootcamp> getBootcampsByInstructor(Long instructorId);
    void deactivate(Long id);
    void activate(Long id);
    List<Bootcamp> getBootcampsByStatus(String status);
} 