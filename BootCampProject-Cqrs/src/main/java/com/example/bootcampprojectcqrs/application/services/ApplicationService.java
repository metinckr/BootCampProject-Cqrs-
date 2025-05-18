package com.example.bootcampprojectcqrs.application.services;

import com.example.bootcampprojectcqrs.domain.entities.Application;

import java.util.List;

public interface ApplicationService extends BaseService<Application, Long> {
    List<Application> getApplicationsByApplicant(Long applicantId);
    List<Application> getApplicationsByBootcamp(Long bootcampId);
    List<Application> getApplicationsByStatus(String status);
    void updateStatus(Long id, String status);
} 