package com.example.bootcampprojectcqrs.application.businessrules;

import com.example.bootcampprojectcqrs.application.exceptions.BusinessException;
import com.example.bootcampprojectcqrs.application.services.TranslationService;
import com.example.bootcampprojectcqrs.domain.entities.Instructor;
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
class InstructorBusinessRulesTest {

    @Mock
    private InstructorRepository instructorRepository;

    @Mock
    private TranslationService translationService;

    @InjectMocks
    private InstructorBusinessRules instructorBusinessRules;

    private Instructor instructor;

    @BeforeEach
    void setUp() {
        instructor = new Instructor();
        instructor.setId(1L);
        instructor.setEmail("test@example.com");
        instructor.setActive(true);
    }

    @Test
    void checkIfInstructorExists_WhenExists_ShouldNotThrowException() {
        when(instructorRepository.existsById(1L)).thenReturn(true);
        assertDoesNotThrow(() -> instructorBusinessRules.checkIfInstructorExists(1L));
    }

    @Test
    void checkIfInstructorExists_WhenNotExists_ShouldThrowException() {
        when(instructorRepository.existsById(1L)).thenReturn(false);
        when(translationService.getMessage("instructor.not.found")).thenReturn("Instructor not found");

        BusinessException exception = assertThrows(BusinessException.class,
                () -> instructorBusinessRules.checkIfInstructorExists(1L));

        assertEquals("Instructor not found", exception.getMessage());
    }

    @Test
    void checkIfInstructorAlreadyExists_WhenExists_ShouldThrowException() {
        when(instructorRepository.existsByEmail("test@example.com")).thenReturn(true);
        when(translationService.getMessage("instructor.already.exists")).thenReturn("Instructor already exists");

        BusinessException exception = assertThrows(BusinessException.class,
                () -> instructorBusinessRules.checkIfInstructorAlreadyExists("test@example.com"));

        assertEquals("Instructor already exists", exception.getMessage());
    }

    @Test
    void checkIfInstructorAlreadyExists_WhenNotExists_ShouldNotThrowException() {
        when(instructorRepository.existsByEmail("test@example.com")).thenReturn(false);
        assertDoesNotThrow(() -> instructorBusinessRules.checkIfInstructorAlreadyExists("test@example.com"));
    }

    @Test
    void checkIfInstructorIsActive_WhenActive_ShouldNotThrowException() {
        when(instructorRepository.findById(1L)).thenReturn(Optional.of(instructor));
        assertDoesNotThrow(() -> instructorBusinessRules.checkIfInstructorIsActive(1L));
    }

    @Test
    void checkIfInstructorIsActive_WhenNotActive_ShouldThrowException() {
        instructor.setActive(false);
        when(instructorRepository.findById(1L)).thenReturn(Optional.of(instructor));
        when(translationService.getMessage("instructor.not.active")).thenReturn("Instructor is not active");

        BusinessException exception = assertThrows(BusinessException.class,
                () -> instructorBusinessRules.checkIfInstructorIsActive(1L));

        assertEquals("Instructor is not active", exception.getMessage());
    }
} 