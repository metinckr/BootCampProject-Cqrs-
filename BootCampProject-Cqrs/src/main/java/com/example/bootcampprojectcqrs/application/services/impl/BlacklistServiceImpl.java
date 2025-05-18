package com.example.bootcampprojectcqrs.application.services.impl;

import com.example.bootcampprojectcqrs.application.businessrules.BlacklistBusinessRules;
import com.example.bootcampprojectcqrs.application.services.BlacklistService;
import com.example.bootcampprojectcqrs.domain.entities.Blacklist;
import com.example.bootcampprojectcqrs.domain.repositories.BlacklistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BlacklistServiceImpl implements BlacklistService {
    private final BlacklistRepository blacklistRepository;
    private final BlacklistBusinessRules blacklistBusinessRules;

    @Override
    public Blacklist create(Blacklist blacklist) {
        blacklistBusinessRules.checkIfApplicantExists(blacklist.getApplicant().getId());
        blacklistBusinessRules.checkIfApplicantIsActive(blacklist.getApplicant().getId());
        blacklistBusinessRules.checkIfApplicantAlreadyBlacklisted(blacklist.getApplicant().getId());
        return blacklistRepository.save(blacklist);
    }

    @Override
    public Blacklist update(Long id, Blacklist blacklist) {
        blacklistBusinessRules.checkIfBlacklistExists(id);
        blacklistBusinessRules.checkIfBlacklistIsActive(id);
        blacklistBusinessRules.checkIfApplicantExists(blacklist.getApplicant().getId());
        blacklistBusinessRules.checkIfApplicantIsActive(blacklist.getApplicant().getId());
        blacklist.setId(id);
        return blacklistRepository.save(blacklist);
    }

    @Override
    public void delete(Long id) {
        blacklistBusinessRules.checkIfBlacklistExists(id);
        blacklistRepository.deleteById(id);
    }

    @Override
    public Blacklist getById(Long id) {
        blacklistBusinessRules.checkIfBlacklistExists(id);
        return blacklistRepository.findById(id).get();
    }

    @Override
    public List<Blacklist> getAll() {
        return blacklistRepository.findAll();
    }

    @Override
    public List<Blacklist> getBlacklistByApplicant(Long applicantId) {
        blacklistBusinessRules.checkIfApplicantExists(applicantId);
        return blacklistRepository.findByApplicantId(applicantId);
    }

    @Override
    public List<Blacklist> getActiveBlacklistEntries() {
        return blacklistRepository.findByActiveTrue();
    }

    @Override
    public void deactivate(Long id) {
        Blacklist blacklist = getById(id);
        blacklist.setActive(false);
        blacklistRepository.save(blacklist);
    }

    @Override
    public void activate(Long id) {
        Blacklist blacklist = getById(id);
        blacklist.setActive(true);
        blacklistRepository.save(blacklist);
    }

    @Override
    public boolean isApplicantBlacklisted(Long applicantId) {
        blacklistBusinessRules.checkIfApplicantExists(applicantId);
        return blacklistRepository.existsByApplicantIdAndActiveTrue(applicantId);
    }
} 