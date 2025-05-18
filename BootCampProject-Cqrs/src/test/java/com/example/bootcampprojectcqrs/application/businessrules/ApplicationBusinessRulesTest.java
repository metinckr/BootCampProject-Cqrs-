package com.example.bootcampprojectcqrs.application.businessrules;

import com.example.bootcampprojectcqrs.application.exceptions.BusinessException;
import com.example.bootcampprojectcqrs.application.services.TranslationService;
import com.example.bootcampprojectcqrs.domain.entities.Application;
import com.example.bootcampprojectcqrs.domain.repositories.ApplicationRepository;
import com.example.bootcampprojectcqrs.domain.repositories.ApplicantRepository;
import com.example.bootcampprojectcqrs.domain.repositories.BootcampRepository;
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
class ApplicationBusinessRulesTest {

    @Mock
    private ApplicationRepository applicationRepository;

    @Mock
    private ApplicantRepository applicantRepository;

    @Mock
    private BootcampRepository bootcampRepository;

    @Mock
    private TranslationService translationService;

    @InjectMocks
    private ApplicationBusinessRules applicationBusinessRules;

    private Application application;

    @BeforeEach
    void setUp() {
        application = new Application();
        application.setId(1L);
    }

    @Test
    void checkIfApplicationExists_WhenExists_ShouldNotThrowException() {
        when(applicationRepository.existsById(1L)).thenReturn(true);
        assertDoesNotThrow(() -> applicationBusinessRules.checkIfApplicationExists(1L));
    }

    @Test
    void checkIfApplicationExists_WhenNotExists_ShouldThrowException() {
        when(applicationRepository.existsById(1L)).thenReturn(false);
        when(translationService.getMessage("application.not.found")).thenReturn("Application not found");

        BusinessException exception = assertThrows(BusinessException.class,
                () -> applicationBusinessRules.checkIfApplicationExists(1L));

        assertEquals("Application not found", exception.getMessage());
    }

    @Test
    void checkIfApplicantExists_WhenExists_ShouldNotThrowException() {
        when(applicantRepository.existsById(1L)).thenReturn(true);
        assertDoesNotThrow(() -> applicationBusinessRules.checkIfApplicantExists(1L));
    }

    @Test
    void checkIfApplicantExists_WhenNotExists_ShouldThrowException() {
        when(applicantRepository.existsById(1L)).thenReturn(false);
        when(translationService.getMessage("applicant.not.found")).thenReturn("Applicant not found");

        BusinessException exception = assertThrows(BusinessException.class,
                () -> applicationBusinessRules.checkIfApplicantExists(1L));

        assertEquals("Applicant not found", exception.getMessage());
    }

    @Test
    void checkIfBootcampExists_WhenExists_ShouldNotThrowException() {
        when(bootcampRepository.existsById(1L)).thenReturn(true);
        assertDoesNotThrow(() -> applicationBusinessRules.checkIfBootcampExists(1L));
    }

    @Test
    void checkIfBootcampExists_WhenNotExists_ShouldThrowException() {
        when(bootcampRepository.existsById(1L)).thenReturn(false);
        when(translationService.getMessage("bootcamp.not.found")).thenReturn("Bootcamp not found");

        BusinessException exception = assertThrows(BusinessException.class,
                () -> applicationBusinessRules.checkIfBootcampExists(1L));

        assertEquals("Bootcamp not found", exception.getMessage());
    }

    @Test
    void checkIfApplicantAlreadyApplied_WhenNotApplied_ShouldNotThrowException() {
        when(applicationRepository.existsByApplicantIdAndBootcampId(1L, 1L)).thenReturn(false);
        assertDoesNotThrow(() -> applicationBusinessRules.checkIfApplicantAlreadyApplied(1L, 1L));
    }

    @Test
    void checkIfApplicantAlreadyApplied_WhenApplied_ShouldThrowException() {
        when(applicationRepository.existsByApplicantIdAndBootcampId(1L, 1L)).thenReturn(true);
        when(translationService.getMessage("application.already.exists")).thenReturn("Application already exists");

        BusinessException exception = assertThrows(BusinessException.class,
                () -> applicationBusinessRules.checkIfApplicantAlreadyApplied(1L, 1L));

        assertEquals("Application already exists", exception.getMessage());
    }

    @Test
    void checkIfStatusIsValid_WhenValid_ShouldNotThrowException() {
        assertDoesNotThrow(() -> applicationBusinessRules.checkIfStatusIsValid("PENDING"));
    }

    @Test
    void checkIfStatusIsValid_WhenInvalid_ShouldThrowException() {
        when(translationService.getMessage("application.status.invalid")).thenReturn("Invalid application status");

        BusinessException exception = assertThrows(BusinessException.class,
                () -> applicationBusinessRules.checkIfStatusIsValid("INVALID_STATUS"));

        assertEquals("Invalid application status", exception.getMessage());
    }
} 