package com.example.bootcampprojectcqrs.application.dtos.requests;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateInstructorRequest {
    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
    private String phoneNumber;

    @NotBlank(message = "Expertise is required")
    @Size(min = 3, max = 100, message = "Expertise must be between 3 and 100 characters")
    private String expertise;

    @NotBlank(message = "Bio is required")
    @Size(min = 10, max = 500, message = "Bio must be between 10 and 500 characters")
    private String bio;

    @Pattern(regexp = "^https?://(www\\.)?linkedin\\.com/.*$", message = "Invalid LinkedIn URL format")
    private String linkedinUrl;

    @Pattern(regexp = "^https?://(www\\.)?github\\.com/.*$", message = "Invalid GitHub URL format")
    private String githubUrl;
} 