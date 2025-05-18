package com.example.bootcampprojectcqrs.application.dtos.requests;

import com.example.bootcampprojectcqrs.domain.entities.Application;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateApplicationRequest {
    @NotNull(message = "Applicant ID is required")
    private Long applicantId;

    @NotNull(message = "Bootcamp ID is required")
    private Long bootcampId;

    @Size(max = 1000, message = "Motivation letter must not exceed 1000 characters")
    private String motivationLetter;

    @Size(max = 500, message = "Additional notes must not exceed 500 characters")
    private String additionalNotes;

    @NotNull(message = "Application status is required")
    private Application.ApplicationStatus status;
} 