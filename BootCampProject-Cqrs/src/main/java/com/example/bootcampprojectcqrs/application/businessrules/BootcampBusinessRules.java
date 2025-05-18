package com.example.bootcampprojectcqrs.application.businessrules;

import com.example.bootcampprojectcqrs.domain.repositories.BootcampRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class BootcampBusinessRules extends BaseBusinessRules {
    private final BootcampRepository bootcampRepository;

    public void checkIfBootcampExists(Long id) {
        if (!bootcampRepository.existsById(id)) {
            throwBusinessException("bootcamp.not.found");
        }
    }

    public void checkIfBootcampNameExists(String name) {
        if (bootcampRepository.existsByName(name)) {
            throwBusinessException("bootcamp.already.exists");
        }
    }

    public void checkIfBootcampIsActive(Long id) {
        if (!bootcampRepository.findById(id).get().isActive()) {
            throwBusinessException("bootcamp.not.active");
        }
    }

    public void validateBootcampDates(LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate)) {
            throwBusinessException("bootcamp.invalid.dates");
        }
        if (startDate.isBefore(LocalDate.now())) {
            throwBusinessException("bootcamp.start.date.past");
        }
    }

    public void validateBootcampCapacity(Integer capacity) {
        if (capacity <= 0) {
            throwBusinessException("bootcamp.invalid.capacity");
        }
    }
} 