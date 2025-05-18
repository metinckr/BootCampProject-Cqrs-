package com.example.bootcampprojectcqrs.domain.repositories;

import com.example.bootcampprojectcqrs.domain.entities.Blacklist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BlacklistRepository extends JpaRepository<Blacklist, Long> {
    boolean existsByApplicantId(Long applicantId);
    List<Blacklist> findByExpiryDateBeforeAndIsActiveTrue(LocalDateTime date);
    List<Blacklist> findByIsActiveTrue();
} 