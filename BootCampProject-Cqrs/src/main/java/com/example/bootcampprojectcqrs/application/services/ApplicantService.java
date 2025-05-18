package com.example.bootcampprojectcqrs.application.services;

import com.example.bootcampprojectcqrs.domain.entities.Applicant;

public interface ApplicantService extends BaseService<Applicant, Long> {
    Applicant getByNationalId(String nationalId);
    Applicant getByEmail(String email);
    void deactivate(Long id);
    void activate(Long id);
} 