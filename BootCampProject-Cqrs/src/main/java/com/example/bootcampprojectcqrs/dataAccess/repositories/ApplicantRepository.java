package com.example.bootcampprojectcqrs.dataAccess.repositories;

import com.example.bootcampprojectcqrs.domain.entities.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicantRepository extends JpaRepository<Applicant, Long> {
    boolean existsByNationalId(String nationalId);
    boolean existsByEmail(String email);
} 