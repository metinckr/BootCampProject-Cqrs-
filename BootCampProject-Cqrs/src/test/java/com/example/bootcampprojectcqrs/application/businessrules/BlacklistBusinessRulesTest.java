package com.example.bootcampprojectcqrs.application.businessrules;

import com.example.bootcampprojectcqrs.application.exceptions.BusinessException;
import com.example.bootcampprojectcqrs.application.services.TranslationService;
import com.example.bootcampprojectcqrs.domain.entities.Blacklist;
import com.example.bootcampprojectcqrs.domain.repositories.BlacklistRepository;
import com.example.bootcampprojectcqrs.domain.repositories.ApplicantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BlacklistBusinessRulesTest {

    @Mock
    private BlacklistRepository blacklistRepository;

    @Mock
    private ApplicantRepository applicantRepository;

    @Mock
    private TranslationService translationService;

    @InjectMocks
    private BlacklistBusinessRules blacklistBusinessRules;

    private Blacklist blacklist;

    @BeforeEach
    void setUp() {
        blacklist = new Blacklist();
        blacklist.setId(1L);
        blacklist.setActive(true);
    }

    @Test
    void checkIfBlacklistExists_WhenExists_ShouldNotThrowException() {
        when(blacklistRepository.existsById(1L)).thenReturn(true);
        assertDoesNotThrow(() -> blacklistBusinessRules.checkIfBlacklistExists(1L));
    }

    @Test
    void checkIfBlacklistExists_WhenNotExists_ShouldThrowException() {
        when(blacklistRepository.existsById(1L)).thenReturn(false);
        when(translationService.getMessage("blacklist.not.found")).thenReturn("Blacklist entry not found");

        BusinessException exception = assertThrows(BusinessException.class,
                () -> blacklistBusinessRules.checkIfBlacklistExists(1L));

        assertEquals("Blacklist entry not found", exception.getMessage());
    }

    @Test
    void checkIfApplicantExists_WhenExists_ShouldNotThrowException() {
        when(applicantRepository.existsById(1L)).thenReturn(true);
        assertDoesNotThrow(() -> blacklistBusinessRules.checkIfApplicantExists(1L));
    }

    @Test
    void checkIfApplicantExists_WhenNotExists_ShouldThrowException() {
        when(applicantRepository.existsById(1L)).thenReturn(false);
        when(translationService.getMessage("applicant.not.found")).thenReturn("Applicant not found");

        BusinessException exception = assertThrows(BusinessException.class,
                () -> blacklistBusinessRules.checkIfApplicantExists(1L));

        assertEquals("Applicant not found", exception.getMessage());
    }

    @Test
    void checkIfApplicantAlreadyBlacklisted_WhenNotBlacklisted_ShouldNotThrowException() {
        when(blacklistRepository.existsByApplicantIdAndActiveTrue(1L)).thenReturn(false);
        assertDoesNotThrow(() -> blacklistBusinessRules.checkIfApplicantAlreadyBlacklisted(1L));
    }

    @Test
    void checkIfApplicantAlreadyBlacklisted_WhenBlacklisted_ShouldThrowException() {
        when(blacklistRepository.existsByApplicantIdAndActiveTrue(1L)).thenReturn(true);
        when(translationService.getMessage("blacklist.already.exists")).thenReturn("Applicant is already blacklisted");

        BusinessException exception = assertThrows(BusinessException.class,
                () -> blacklistBusinessRules.checkIfApplicantAlreadyBlacklisted(1L));

        assertEquals("Applicant is already blacklisted", exception.getMessage());
    }

    @Test
    void checkIfBlacklistIsActive_WhenActive_ShouldNotThrowException() {
        when(blacklistRepository.findById(1L)).thenReturn(Optional.of(blacklist));
        assertDoesNotThrow(() -> blacklistBusinessRules.checkIfBlacklistIsActive(1L));
    }

    @Test
    void checkIfBlacklistIsActive_WhenNotActive_ShouldThrowException() {
        blacklist.setActive(false);
        when(blacklistRepository.findById(1L)).thenReturn(Optional.of(blacklist));
        when(translationService.getMessage("blacklist.not.active")).thenReturn("Blacklist entry is not active");

        BusinessException exception = assertThrows(BusinessException.class,
                () -> blacklistBusinessRules.checkIfBlacklistIsActive(1L));

        assertEquals("Blacklist entry is not active", exception.getMessage());
    }
} 