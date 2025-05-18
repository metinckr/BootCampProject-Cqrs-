package com.example.bootcampprojectcqrs.application.services;

import com.example.bootcampprojectcqrs.domain.entities.Instructor;

import java.util.List;

public interface InstructorService extends BaseService<Instructor, Long> {
    Instructor getByEmail(String email);
    List<Instructor> getActiveInstructors();
    void deactivate(Long id);
    void activate(Long id);
} 