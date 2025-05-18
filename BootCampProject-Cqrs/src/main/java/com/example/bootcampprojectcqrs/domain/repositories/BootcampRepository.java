package com.example.bootcampprojectcqrs.domain.repositories;

import com.example.bootcampprojectcqrs.domain.entities.Bootcamp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BootcampRepository extends JpaRepository<Bootcamp, Long> {
    boolean existsByName(String name);
    List<Bootcamp> findByInstructorId(Long instructorId);
    List<Bootcamp> findByIsActiveTrue();
} 