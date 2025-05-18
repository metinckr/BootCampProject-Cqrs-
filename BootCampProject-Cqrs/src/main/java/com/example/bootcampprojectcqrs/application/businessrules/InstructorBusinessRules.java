package com.example.bootcampprojectcqrs.application.businessrules;

import com.example.bootcampprojectcqrs.domain.repositories.InstructorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InstructorBusinessRules extends BaseBusinessRules {
    private final InstructorRepository instructorRepository;

    public void checkIfInstructorExists(Long id) {
        if (!instructorRepository.existsById(id)) {
            throwBusinessException("instructor.not.found");
        }
    }

    public void checkIfInstructorEmailExists(String email) {
        if (instructorRepository.existsByEmail(email)) {
            throwBusinessException("instructor.already.exists");
        }
    }

    public void checkIfInstructorPhoneExists(String phoneNumber) {
        if (instructorRepository.existsByPhoneNumber(phoneNumber)) {
            throwBusinessException("instructor.phone.exists");
        }
    }

    public void checkIfInstructorIsActive(Long id) {
        if (!instructorRepository.findById(id).get().isActive()) {
            throwBusinessException("instructor.not.active");
        }
    }
} 