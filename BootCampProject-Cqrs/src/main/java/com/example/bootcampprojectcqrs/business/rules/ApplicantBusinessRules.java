package com.example.bootcampprojectcqrs.business.rules;

import com.example.bootcampprojectcqrs.dataAccess.repositories.ApplicantRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ApplicantBusinessRules extends BaseBusinessRules {
    private final ApplicantRepository applicantRepository;

    public void checkIfApplicantExists(String nationalId) {
        if (applicantRepository.existsByNationalId(nationalId)) {
            throwBusinessException("business.applicant.alreadyExists");
        }
    }

    public void checkIfApplicantNotExists(Long id) {
        if (!applicantRepository.existsById(id)) {
            throwBusinessException("business.applicant.notFound");
        }
    }

    public void checkIfEmailExists(String email) {
        if (applicantRepository.existsByEmail(email)) {
            throwBusinessException("business.applicant.emailAlreadyExists");
        }
    }
} 