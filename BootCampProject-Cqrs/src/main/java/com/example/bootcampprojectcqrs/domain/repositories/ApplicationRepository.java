package com.example.bootcampprojectcqrs.domain.repositories;

import com.example.bootcampprojectcqrs.domain.entities.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    boolean existsByApplicantIdAndBootcampId(Long applicantId, Long bootcampId);
    List<Application> findByApplicantId(Long applicantId);
    List<Application> findByBootcampId(Long bootcampId);
    List<Application> findByStatus(Application.ApplicationStatus status);
} 