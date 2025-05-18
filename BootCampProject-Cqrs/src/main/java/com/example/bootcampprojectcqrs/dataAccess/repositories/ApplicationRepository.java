package com.example.bootcampprojectcqrs.dataAccess.repositories;

import com.example.bootcampprojectcqrs.domain.entities.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    boolean existsByApplicantIdAndBootcampId(Long applicantId, Long bootcampId);
} 