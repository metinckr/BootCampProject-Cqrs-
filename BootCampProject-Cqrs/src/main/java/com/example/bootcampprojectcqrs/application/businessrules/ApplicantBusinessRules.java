package com.example.bootcampprojectcqrs.application.businessrules;

import com.example.bootcampprojectcqrs.domain.repositories.ApplicantRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ApplicantBusinessRules extends BaseBusinessRules {
    private final ApplicantRepository applicantRepository;

    public void checkIfApplicantExists(Long id) {
        if (!applicantRepository.existsById(id)) {
            throwBusinessException("applicant.not.found");
        }
    }

    public void checkIfApplicantAlreadyExists(String nationalId) {
        if (applicantRepository.existsByNationalId(nationalId)) {
            throwBusinessException("applicant.already.exists");
        }
    }

    public void checkIfApplicantIsActive(Long id) {
        if (!applicantRepository.findById(id).get().isActive()) {
            throwBusinessException("applicant.not.active");
        }
    }
} 