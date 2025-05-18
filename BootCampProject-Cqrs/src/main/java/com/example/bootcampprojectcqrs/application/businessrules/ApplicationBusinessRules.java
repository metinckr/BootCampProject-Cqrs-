package com.example.bootcampprojectcqrs.application.businessrules;

import com.example.bootcampprojectcqrs.domain.entities.Application;
import com.example.bootcampprojectcqrs.domain.repositories.ApplicationRepository;
import com.example.bootcampprojectcqrs.domain.repositories.BlacklistRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ApplicationBusinessRules extends BaseBusinessRules {
    private final ApplicationRepository applicationRepository;
    private final BlacklistRepository blacklistRepository;

    public void checkIfApplicationExists(Long id) {
        if (!applicationRepository.existsById(id)) {
            throwBusinessException("application.not.found");
        }
    }

    public void checkIfApplicationAlreadyExists(Long applicantId, Long bootcampId) {
        if (applicationRepository.existsByApplicantIdAndBootcampId(applicantId, bootcampId)) {
            throwBusinessException("application.already.exists");
        }
    }

    public void checkIfApplicationIsActive(Long id) {
        if (!applicationRepository.findById(id).get().isActive()) {
            throwBusinessException("application.not.active");
        }
    }

    public void checkIfApplicantIsBlacklisted(Long applicantId) {
        if (blacklistRepository.existsByApplicantId(applicantId)) {
            throwBusinessException("application.applicant.blacklisted");
        }
    }

    public void validateApplicationStatus(Application.ApplicationStatus status) {
        if (status == null) {
            throwBusinessException("application.status.invalid");
        }
    }
} 