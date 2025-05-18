package com.example.bootcampprojectcqrs.dataAccess.repositories;

import com.example.bootcampprojectcqrs.domain.entities.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long> {
    boolean existsByEmail(String email);
} 