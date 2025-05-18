package com.example.bootcampprojectcqrs.application.services.impl;

import com.example.bootcampprojectcqrs.application.businessrules.ApplicantBusinessRules;
import com.example.bootcampprojectcqrs.application.dtos.requests.CreateApplicantRequest;
import com.example.bootcampprojectcqrs.application.services.ApplicantService;
import com.example.bootcampprojectcqrs.domain.entities.Applicant;
import com.example.bootcampprojectcqrs.domain.repositories.ApplicantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ApplicantServiceImpl implements ApplicantService {
    private final ApplicantRepository applicantRepository;
    private final ApplicantBusinessRules applicantBusinessRules;

    @Override
    public Applicant create(Applicant applicant) {
        applicantBusinessRules.checkIfApplicantAlreadyExists(applicant.getNationalId());
        return applicantRepository.save(applicant);
    }

    @Override
    public Applicant update(Long id, Applicant applicant) {
        applicantBusinessRules.checkIfApplicantExists(id);
        applicantBusinessRules.checkIfApplicantIsActive(id);
        applicant.setId(id);
        return applicantRepository.save(applicant);
    }

    @Override
    public void delete(Long id) {
        applicantBusinessRules.checkIfApplicantExists(id);
        applicantRepository.deleteById(id);
    }

    @Override
    public Applicant getById(Long id) {
        applicantBusinessRules.checkIfApplicantExists(id);
        return applicantRepository.findById(id).get();
    }

    @Override
    public List<Applicant> getAll() {
        return applicantRepository.findAll();
    }

    @Override
    public Applicant getByNationalId(String nationalId) {
        return applicantRepository.findByNationalId(nationalId)
                .orElseThrow(() -> applicantBusinessRules.throwBusinessException("applicant.not.found"));
    }

    @Override
    public Applicant getByEmail(String email) {
        return applicantRepository.findByEmail(email)
                .orElseThrow(() -> applicantBusinessRules.throwBusinessException("applicant.not.found"));
    }

    @Override
    public void deactivate(Long id) {
        Applicant applicant = getById(id);
        applicant.setActive(false);
        applicantRepository.save(applicant);
    }

    @Override
    public void activate(Long id) {
        Applicant applicant = getById(id);
        applicant.setActive(true);
        applicantRepository.save(applicant);
    }
} 