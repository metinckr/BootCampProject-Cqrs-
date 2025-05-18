package com.example.bootcampprojectcqrs.application.businessrules;

import com.example.bootcampprojectcqrs.application.exceptions.BusinessException;
import com.example.bootcampprojectcqrs.application.services.TranslationService;
import com.example.bootcampprojectcqrs.domain.entities.Applicant;
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
class ApplicantBusinessRulesTest {

    @Mock
    private ApplicantRepository applicantRepository;

    @Mock
    private TranslationService translationService;

    @InjectMocks
    private ApplicantBusinessRules applicantBusinessRules;

    private Applicant applicant;

    @BeforeEach
    void setUp() {
        applicant = new Applicant();
        applicant.setId(1L);
        applicant.setNationalId("12345678901");
        applicant.setActive(true);
    }

    @Test
    void checkIfApplicantExists_WhenExists_ShouldNotThrowException() {
        when(applicantRepository.existsById(1L)).thenReturn(true);
        assertDoesNotThrow(() -> applicantBusinessRules.checkIfApplicantExists(1L));
    }

    @Test
    void checkIfApplicantExists_WhenNotExists_ShouldThrowException() {
        when(applicantRepository.existsById(1L)).thenReturn(false);
        when(translationService.getMessage("applicant.not.found")).thenReturn("Applicant not found");

        BusinessException exception = assertThrows(BusinessException.class,
                () -> applicantBusinessRules.checkIfApplicantExists(1L));

        assertEquals("Applicant not found", exception.getMessage());
    }

    @Test
    void checkIfApplicantAlreadyExists_WhenExists_ShouldThrowException() {
        when(applicantRepository.existsByNationalId("12345678901")).thenReturn(true);
        when(translationService.getMessage("applicant.already.exists")).thenReturn("Applicant already exists");

        BusinessException exception = assertThrows(BusinessException.class,
                () -> applicantBusinessRules.checkIfApplicantAlreadyExists("12345678901"));

        assertEquals("Applicant already exists", exception.getMessage());
    }

    @Test
    void checkIfApplicantAlreadyExists_WhenNotExists_ShouldNotThrowException() {
        when(applicantRepository.existsByNationalId("12345678901")).thenReturn(false);
        assertDoesNotThrow(() -> applicantBusinessRules.checkIfApplicantAlreadyExists("12345678901"));
    }

    @Test
    void checkIfApplicantIsActive_WhenActive_ShouldNotThrowException() {
        when(applicantRepository.findById(1L)).thenReturn(Optional.of(applicant));
        assertDoesNotThrow(() -> applicantBusinessRules.checkIfApplicantIsActive(1L));
    }

    @Test
    void checkIfApplicantIsActive_WhenNotActive_ShouldThrowException() {
        applicant.setActive(false);
        when(applicantRepository.findById(1L)).thenReturn(Optional.of(applicant));
        when(translationService.getMessage("applicant.not.active")).thenReturn("Applicant is not active");

        BusinessException exception = assertThrows(BusinessException.class,
                () -> applicantBusinessRules.checkIfApplicantIsActive(1L));

        assertEquals("Applicant is not active", exception.getMessage());
    }
} 