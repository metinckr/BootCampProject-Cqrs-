package com.example.bootcampprojectcqrs.application.services.impl;

import com.example.bootcampprojectcqrs.application.businessrules.ApplicationBusinessRules;
import com.example.bootcampprojectcqrs.application.services.ApplicationService;
import com.example.bootcampprojectcqrs.domain.entities.Application;
import com.example.bootcampprojectcqrs.domain.repositories.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ApplicationServiceImpl implements ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final ApplicationBusinessRules applicationBusinessRules;

    @Override
    public Application create(Application application) {
        applicationBusinessRules.checkIfApplicantExists(application.getApplicant().getId());
        applicationBusinessRules.checkIfBootcampExists(application.getBootcamp().getId());
        applicationBusinessRules.checkIfApplicantIsActive(application.getApplicant().getId());
        applicationBusinessRules.checkIfBootcampIsActive(application.getBootcamp().getId());
        applicationBusinessRules.checkIfApplicantAlreadyApplied(application.getApplicant().getId(), application.getBootcamp().getId());
        applicationBusinessRules.checkIfBootcampHasCapacity(application.getBootcamp().getId());
        return applicationRepository.save(application);
    }

    @Override
    public Application update(Long id, Application application) {
        applicationBusinessRules.checkIfApplicationExists(id);
        applicationBusinessRules.checkIfApplicantExists(application.getApplicant().getId());
        applicationBusinessRules.checkIfBootcampExists(application.getBootcamp().getId());
        applicationBusinessRules.checkIfApplicantIsActive(application.getApplicant().getId());
        applicationBusinessRules.checkIfBootcampIsActive(application.getBootcamp().getId());
        application.setId(id);
        return applicationRepository.save(application);
    }

    @Override
    public void delete(Long id) {
        applicationBusinessRules.checkIfApplicationExists(id);
        applicationRepository.deleteById(id);
    }

    @Override
    public Application getById(Long id) {
        applicationBusinessRules.checkIfApplicationExists(id);
        return applicationRepository.findById(id).get();
    }

    @Override
    public List<Application> getAll() {
        return applicationRepository.findAll();
    }

    @Override
    public List<Application> getApplicationsByApplicant(Long applicantId) {
        applicationBusinessRules.checkIfApplicantExists(applicantId);
        return applicationRepository.findByApplicantId(applicantId);
    }

    @Override
    public List<Application> getApplicationsByBootcamp(Long bootcampId) {
        applicationBusinessRules.checkIfBootcampExists(bootcampId);
        return applicationRepository.findByBootcampId(bootcampId);
    }

    @Override
    public List<Application> getApplicationsByStatus(String status) {
        return applicationRepository.findByStatus(status);
    }

    @Override
    public void updateStatus(Long id, String status) {
        Application application = getById(id);
        applicationBusinessRules.checkIfStatusIsValid(status);
        application.setStatus(status);
        applicationRepository.save(application);
    }
} 