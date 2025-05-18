package com.example.bootcampprojectcqrs.dataAccess.repositories;

import com.example.bootcampprojectcqrs.domain.entities.Blacklist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlacklistRepository extends JpaRepository<Blacklist, Long> {
    boolean existsByApplicantIdAndIsActiveTrue(Long applicantId);
} 