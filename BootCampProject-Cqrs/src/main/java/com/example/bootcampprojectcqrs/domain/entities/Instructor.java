package com.example.bootcampprojectcqrs.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "instructors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String expertise;

    @Column(nullable = false)
    private String bio;

    @Column(nullable = false)
    private boolean isActive = true;

    private String linkedinUrl;
    private String githubUrl;

    @OneToMany(mappedBy = "instructor")
    private List<Bootcamp> bootcamps;
} 