package com.example.bootcampprojectcqrs.business.rules;

import com.example.bootcampprojectcqrs.dataAccess.repositories.InstructorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InstructorBusinessRules extends BaseBusinessRules {
    private final InstructorRepository instructorRepository;

    public void checkIfInstructorExists(Long id) {
        if (!instructorRepository.existsById(id)) {
            throwBusinessException("business.instructor.notFound");
        }
    }

    public void checkIfEmailExists(String email) {
        if (instructorRepository.existsByEmail(email)) {
            throwBusinessException("business.instructor.emailAlreadyExists");
        }
    }

    public void validateInstructorExpertise(String expertise) {
        if (expertise == null || expertise.trim().isEmpty()) {
            throwBusinessException("business.instructor.expertiseRequired");
        }
    }
} 