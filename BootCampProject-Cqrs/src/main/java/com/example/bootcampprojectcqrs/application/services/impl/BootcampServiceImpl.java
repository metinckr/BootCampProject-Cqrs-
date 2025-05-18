package com.example.bootcampprojectcqrs.application.services.impl;

import com.example.bootcampprojectcqrs.application.businessrules.BootcampBusinessRules;
import com.example.bootcampprojectcqrs.application.services.BootcampService;
import com.example.bootcampprojectcqrs.domain.entities.Bootcamp;
import com.example.bootcampprojectcqrs.domain.repositories.BootcampRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BootcampServiceImpl implements BootcampService {
    private final BootcampRepository bootcampRepository;
    private final BootcampBusinessRules bootcampBusinessRules;

    @Override
    public Bootcamp create(Bootcamp bootcamp) {
        bootcampBusinessRules.checkIfBootcampAlreadyExists(bootcamp.getName());
        bootcampBusinessRules.checkIfInstructorExists(bootcamp.getInstructor().getId());
        return bootcampRepository.save(bootcamp);
    }

    @Override
    public Bootcamp update(Long id, Bootcamp bootcamp) {
        bootcampBusinessRules.checkIfBootcampExists(id);
        bootcampBusinessRules.checkIfBootcampIsActive(id);
        bootcampBusinessRules.checkIfInstructorExists(bootcamp.getInstructor().getId());
        bootcamp.setId(id);
        return bootcampRepository.save(bootcamp);
    }

    @Override
    public void delete(Long id) {
        bootcampBusinessRules.checkIfBootcampExists(id);
        bootcampRepository.deleteById(id);
    }

    @Override
    public Bootcamp getById(Long id) {
        bootcampBusinessRules.checkIfBootcampExists(id);
        return bootcampRepository.findById(id).get();
    }

    @Override
    public List<Bootcamp> getAll() {
        return bootcampRepository.findAll();
    }

    @Override
    public List<Bootcamp> getActiveBootcamps() {
        return bootcampRepository.findByActiveTrue();
    }

    @Override
    public List<Bootcamp> getBootcampsByInstructor(Long instructorId) {
        bootcampBusinessRules.checkIfInstructorExists(instructorId);
        return bootcampRepository.findByInstructorId(instructorId);
    }

    @Override
    public void deactivate(Long id) {
        Bootcamp bootcamp = getById(id);
        bootcamp.setActive(false);
        bootcampRepository.save(bootcamp);
    }

    @Override
    public void activate(Long id) {
        Bootcamp bootcamp = getById(id);
        bootcamp.setActive(true);
        bootcampRepository.save(bootcamp);
    }

    @Override
    public List<Bootcamp> getBootcampsByStatus(String status) {
        return bootcampRepository.findByStatus(status);
    }
} 