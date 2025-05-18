package com.example.bootcampprojectcqrs.business.rules;

import com.example.bootcampprojectcqrs.dataAccess.repositories.BootcampRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class BootcampBusinessRules extends BaseBusinessRules {
    private final BootcampRepository bootcampRepository;

    public void checkIfBootcampExists(Long id) {
        if (!bootcampRepository.existsById(id)) {
            throwBusinessException("business.bootcamp.notFound");
        }
    }

    public void checkIfBootcampNameExists(String name) {
        if (bootcampRepository.existsByName(name)) {
            throwBusinessException("business.bootcamp.nameAlreadyExists");
        }
    }

    public void validateBootcampDates(LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate)) {
            throwBusinessException("business.bootcamp.invalidDates");
        }
        if (startDate.isBefore(LocalDate.now())) {
            throwBusinessException("business.bootcamp.startDateInPast");
        }
    }

    public void checkBootcampCapacity(Integer capacity) {
        if (capacity <= 0) {
            throwBusinessException("business.bootcamp.invalidCapacity");
        }
    }
} 