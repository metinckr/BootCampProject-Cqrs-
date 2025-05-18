package com.example.bootcampprojectcqrs.business.rules;

import com.example.bootcampprojectcqrs.dataAccess.repositories.BlacklistRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class BlacklistBusinessRules extends BaseBusinessRules {
    private final BlacklistRepository blacklistRepository;

    public void checkIfBlacklistEntryExists(Long id) {
        if (!blacklistRepository.existsById(id)) {
            throwBusinessException("business.blacklist.notFound");
        }
    }

    public void checkIfApplicantAlreadyBlacklisted(Long applicantId) {
        if (blacklistRepository.existsByApplicantIdAndIsActiveTrue(applicantId)) {
            throwBusinessException("business.blacklist.applicantAlreadyBlacklisted");
        }
    }

    public void validateBlacklistDates(LocalDateTime blacklistDate, LocalDateTime expiryDate) {
        if (blacklistDate.isAfter(expiryDate)) {
            throwBusinessException("business.blacklist.invalidDates");
        }
        if (blacklistDate.isBefore(LocalDateTime.now())) {
            throwBusinessException("business.blacklist.blacklistDateInPast");
        }
    }

    public void validateBlacklistReason(String reason) {
        if (reason == null || reason.trim().isEmpty()) {
            throwBusinessException("business.blacklist.reasonRequired");
        }
    }
} 