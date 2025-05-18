package com.example.bootcampprojectcqrs.application.businessrules;

import com.example.bootcampprojectcqrs.application.exceptions.BusinessException;
import com.example.bootcampprojectcqrs.application.services.TranslationService;
import com.example.bootcampprojectcqrs.domain.entities.Bootcamp;
import com.example.bootcampprojectcqrs.domain.repositories.BootcampRepository;
import com.example.bootcampprojectcqrs.domain.repositories.InstructorRepository;
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
class BootcampBusinessRulesTest {

    @Mock
    private BootcampRepository bootcampRepository;

    @Mock
    private InstructorRepository instructorRepository;

    @Mock
    private TranslationService translationService;

    @InjectMocks
    private BootcampBusinessRules bootcampBusinessRules;

    private Bootcamp bootcamp;

    @BeforeEach
    void setUp() {
        bootcamp = new Bootcamp();
        bootcamp.setId(1L);
        bootcamp.setName("Test Bootcamp");
        bootcamp.setActive(true);
    }

    @Test
    void checkIfBootcampExists_WhenExists_ShouldNotThrowException() {
        when(bootcampRepository.existsById(1L)).thenReturn(true);
        assertDoesNotThrow(() -> bootcampBusinessRules.checkIfBootcampExists(1L));
    }

    @Test
    void checkIfBootcampExists_WhenNotExists_ShouldThrowException() {
        when(bootcampRepository.existsById(1L)).thenReturn(false);
        when(translationService.getMessage("bootcamp.not.found")).thenReturn("Bootcamp not found");

        BusinessException exception = assertThrows(BusinessException.class,
                () -> bootcampBusinessRules.checkIfBootcampExists(1L));

        assertEquals("Bootcamp not found", exception.getMessage());
    }

    @Test
    void checkIfBootcampAlreadyExists_WhenExists_ShouldThrowException() {
        when(bootcampRepository.existsByName("Test Bootcamp")).thenReturn(true);
        when(translationService.getMessage("bootcamp.already.exists")).thenReturn("Bootcamp already exists");

        BusinessException exception = assertThrows(BusinessException.class,
                () -> bootcampBusinessRules.checkIfBootcampAlreadyExists("Test Bootcamp"));

        assertEquals("Bootcamp already exists", exception.getMessage());
    }

    @Test
    void checkIfBootcampAlreadyExists_WhenNotExists_ShouldNotThrowException() {
        when(bootcampRepository.existsByName("Test Bootcamp")).thenReturn(false);
        assertDoesNotThrow(() -> bootcampBusinessRules.checkIfBootcampAlreadyExists("Test Bootcamp"));
    }

    @Test
    void checkIfBootcampIsActive_WhenActive_ShouldNotThrowException() {
        when(bootcampRepository.findById(1L)).thenReturn(Optional.of(bootcamp));
        assertDoesNotThrow(() -> bootcampBusinessRules.checkIfBootcampIsActive(1L));
    }

    @Test
    void checkIfBootcampIsActive_WhenNotActive_ShouldThrowException() {
        bootcamp.setActive(false);
        when(bootcampRepository.findById(1L)).thenReturn(Optional.of(bootcamp));
        when(translationService.getMessage("bootcamp.not.active")).thenReturn("Bootcamp is not active");

        BusinessException exception = assertThrows(BusinessException.class,
                () -> bootcampBusinessRules.checkIfBootcampIsActive(1L));

        assertEquals("Bootcamp is not active", exception.getMessage());
    }

    @Test
    void checkIfInstructorExists_WhenExists_ShouldNotThrowException() {
        when(instructorRepository.existsById(1L)).thenReturn(true);
        assertDoesNotThrow(() -> bootcampBusinessRules.checkIfInstructorExists(1L));
    }

    @Test
    void checkIfInstructorExists_WhenNotExists_ShouldThrowException() {
        when(instructorRepository.existsById(1L)).thenReturn(false);
        when(translationService.getMessage("instructor.not.found")).thenReturn("Instructor not found");

        BusinessException exception = assertThrows(BusinessException.class,
                () -> bootcampBusinessRules.checkIfInstructorExists(1L));

        assertEquals("Instructor not found", exception.getMessage());
    }
} 