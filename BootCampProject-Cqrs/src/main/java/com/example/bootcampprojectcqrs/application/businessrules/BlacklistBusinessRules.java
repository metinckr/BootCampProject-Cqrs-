package com.example.bootcampprojectcqrs.application.businessrules;

import com.example.bootcampprojectcqrs.domain.repositories.BlacklistRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class BlacklistBusinessRules extends BaseBusinessRules {
    private final BlacklistRepository blacklistRepository;

    public void checkIfBlacklistExists(Long id) {
        if (!blacklistRepository.existsById(id)) {
            throwBusinessException("blacklist.not.found");
        }
    }

    public void checkIfApplicantAlreadyBlacklisted(Long applicantId) {
        if (blacklistRepository.existsByApplicantId(applicantId)) {
            throwBusinessException("blacklist.already.exists");
        }
    }

    public void checkIfBlacklistIsActive(Long id) {
        if (!blacklistRepository.findById(id).get().isActive()) {
            throwBusinessException("blacklist.not.active");
        }
    }

    public void validateBlacklistDates(LocalDateTime blacklistDate, LocalDateTime expiryDate) {
        if (blacklistDate.isAfter(expiryDate)) {
            throwBusinessException("blacklist.invalid.dates");
        }
        if (blacklistDate.isBefore(LocalDateTime.now())) {
            throwBusinessException("blacklist.date.past");
        }
    }
} 