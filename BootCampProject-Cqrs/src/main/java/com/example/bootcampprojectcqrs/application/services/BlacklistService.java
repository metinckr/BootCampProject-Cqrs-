package com.example.bootcampprojectcqrs.application.services;

import com.example.bootcampprojectcqrs.domain.entities.Blacklist;

import java.util.List;

public interface BlacklistService extends BaseService<Blacklist, Long> {
    List<Blacklist> getBlacklistByApplicant(Long applicantId);
    List<Blacklist> getActiveBlacklistEntries();
    void deactivate(Long id);
    void activate(Long id);
    boolean isApplicantBlacklisted(Long applicantId);
} 