package com.example.bootcampprojectcqrs.business.rules;

import com.example.bootcampprojectcqrs.dataAccess.repositories.ApplicationRepository;
import com.example.bootcampprojectcqrs.dataAccess.repositories.BlacklistRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ApplicationBusinessRules extends BaseBusinessRules {
    private final ApplicationRepository applicationRepository;
    private final BlacklistRepository blacklistRepository;

    public void checkIfApplicationExists(Long id) {
        if (!applicationRepository.existsById(id)) {
            throwBusinessException("business.application.notFound");
        }
    }

    public void checkIfApplicantAlreadyApplied(Long applicantId, Long bootcampId) {
        if (applicationRepository.existsByApplicantIdAndBootcampId(applicantId, bootcampId)) {
            throwBusinessException("business.application.alreadyApplied");
        }
    }

    public void checkIfApplicantIsBlacklisted(Long applicantId) {
        if (blacklistRepository.existsByApplicantIdAndIsActiveTrue(applicantId)) {
            throwBusinessException("business.application.applicantBlacklisted");
        }
    }

    public void validateApplicationStatus(String status) {
        try {
            Application.ApplicationStatus.valueOf(status);
        } catch (IllegalArgumentException e) {
            throwBusinessException("business.application.invalidStatus");
        }
    }
} 