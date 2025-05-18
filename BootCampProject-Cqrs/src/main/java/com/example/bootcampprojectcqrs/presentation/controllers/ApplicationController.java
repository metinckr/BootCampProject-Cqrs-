package com.example.bootcampprojectcqrs.presentation.controllers;

import com.example.bootcampprojectcqrs.application.dtos.requests.CreateApplicationRequest;
import com.example.bootcampprojectcqrs.application.services.ApplicationService;
import com.example.bootcampprojectcqrs.domain.entities.Application;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/applications")
@RequiredArgsConstructor
@Tag(name = "Application", description = "Application management APIs")
public class ApplicationController {
    private final ApplicationService applicationService;

    @PostMapping
    @Operation(summary = "Create a new application")
    public ResponseEntity<Application> create(@Valid @RequestBody CreateApplicationRequest request) {
        Application application = mapToEntity(request);
        return new ResponseEntity<>(applicationService.create(application), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing application")
    public ResponseEntity<Application> update(@PathVariable Long id, @Valid @RequestBody CreateApplicationRequest request) {
        Application application = mapToEntity(request);
        return ResponseEntity.ok(applicationService.update(id, application));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an application")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        applicationService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get an application by ID")
    public ResponseEntity<Application> getById(@PathVariable Long id) {
        return ResponseEntity.ok(applicationService.getById(id));
    }

    @GetMapping
    @Operation(summary = "Get all applications")
    public ResponseEntity<List<Application>> getAll() {
        return ResponseEntity.ok(applicationService.getAll());
    }

    @GetMapping("/applicant/{applicantId}")
    @Operation(summary = "Get applications by applicant ID")
    public ResponseEntity<List<Application>> getApplicationsByApplicant(@PathVariable Long applicantId) {
        return ResponseEntity.ok(applicationService.getApplicationsByApplicant(applicantId));
    }

    @GetMapping("/bootcamp/{bootcampId}")
    @Operation(summary = "Get applications by bootcamp ID")
    public ResponseEntity<List<Application>> getApplicationsByBootcamp(@PathVariable Long bootcampId) {
        return ResponseEntity.ok(applicationService.getApplicationsByBootcamp(bootcampId));
    }

    @GetMapping("/status/{status}")
    @Operation(summary = "Get applications by status")
    public ResponseEntity<List<Application>> getApplicationsByStatus(@PathVariable String status) {
        return ResponseEntity.ok(applicationService.getApplicationsByStatus(status));
    }

    @PutMapping("/{id}/status")
    @Operation(summary = "Update application status")
    public ResponseEntity<Void> updateStatus(@PathVariable Long id, @RequestParam String status) {
        applicationService.updateStatus(id, status);
        return ResponseEntity.ok().build();
    }

    private Application mapToEntity(CreateApplicationRequest request) {
        Application application = new Application();
        application.setApplicant(request.getApplicant());
        application.setBootcamp(request.getBootcamp());
        application.setApplicationDate(request.getApplicationDate());
        application.setStatus(request.getStatus());
        application.setNotes(request.getNotes());
        return application;
    }
} 