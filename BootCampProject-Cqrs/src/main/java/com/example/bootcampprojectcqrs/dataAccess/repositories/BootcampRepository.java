package com.example.bootcampprojectcqrs.dataAccess.repositories;

import com.example.bootcampprojectcqrs.domain.entities.Bootcamp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BootcampRepository extends JpaRepository<Bootcamp, Long> {
    boolean existsByName(String name);
} 