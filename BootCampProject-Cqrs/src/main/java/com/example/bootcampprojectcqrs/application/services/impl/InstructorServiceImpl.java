package com.example.bootcampprojectcqrs.application.services.impl;

import com.example.bootcampprojectcqrs.application.businessrules.InstructorBusinessRules;
import com.example.bootcampprojectcqrs.application.services.InstructorService;
import com.example.bootcampprojectcqrs.domain.entities.Instructor;
import com.example.bootcampprojectcqrs.domain.repositories.InstructorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class InstructorServiceImpl implements InstructorService {
    private final InstructorRepository instructorRepository;
    private final InstructorBusinessRules instructorBusinessRules;

    @Override
    public Instructor create(Instructor instructor) {
        instructorBusinessRules.checkIfInstructorAlreadyExists(instructor.getEmail());
        return instructorRepository.save(instructor);
    }

    @Override
    public Instructor update(Long id, Instructor instructor) {
        instructorBusinessRules.checkIfInstructorExists(id);
        instructorBusinessRules.checkIfInstructorIsActive(id);
        instructor.setId(id);
        return instructorRepository.save(instructor);
    }

    @Override
    public void delete(Long id) {
        instructorBusinessRules.checkIfInstructorExists(id);
        instructorRepository.deleteById(id);
    }

    @Override
    public Instructor getById(Long id) {
        instructorBusinessRules.checkIfInstructorExists(id);
        return instructorRepository.findById(id).get();
    }

    @Override
    public List<Instructor> getAll() {
        return instructorRepository.findAll();
    }

    @Override
    public Instructor getByEmail(String email) {
        return instructorRepository.findByEmail(email)
                .orElseThrow(() -> instructorBusinessRules.throwBusinessException("instructor.not.found"));
    }

    @Override
    public List<Instructor> getActiveInstructors() {
        return instructorRepository.findByActiveTrue();
    }

    @Override
    public void deactivate(Long id) {
        Instructor instructor = getById(id);
        instructor.setActive(false);
        instructorRepository.save(instructor);
    }

    @Override
    public void activate(Long id) {
        Instructor instructor = getById(id);
        instructor.setActive(true);
        instructorRepository.save(instructor);
    }
} 