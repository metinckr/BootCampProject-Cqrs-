package com.example.bootcampprojectcqrs.presentation.controllers;

import com.example.bootcampprojectcqrs.application.dtos.requests.CreateApplicantRequest;
import com.example.bootcampprojectcqrs.application.services.ApplicantService;
import com.example.bootcampprojectcqrs.domain.entities.Applicant;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/applicants")
@RequiredArgsConstructor
@Tag(name = "Applicant", description = "Applicant management APIs")
public class ApplicantController {
    private final ApplicantService applicantService;

    @PostMapping
    @Operation(summary = "Create a new applicant")
    public ResponseEntity<Applicant> create(@Valid @RequestBody CreateApplicantRequest request) {
        Applicant applicant = mapToEntity(request);
        return new ResponseEntity<>(applicantService.create(applicant), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing applicant")
    public ResponseEntity<Applicant> update(@PathVariable Long id, @Valid @RequestBody CreateApplicantRequest request) {
        Applicant applicant = mapToEntity(request);
        return ResponseEntity.ok(applicantService.update(id, applicant));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an applicant")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        applicantService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get an applicant by ID")
    public ResponseEntity<Applicant> getById(@PathVariable Long id) {
        return ResponseEntity.ok(applicantService.getById(id));
    }

    @GetMapping
    @Operation(summary = "Get all applicants")
    public ResponseEntity<List<Applicant>> getAll() {
        return ResponseEntity.ok(applicantService.getAll());
    }

    @GetMapping("/national-id/{nationalId}")
    @Operation(summary = "Get an applicant by national ID")
    public ResponseEntity<Applicant> getByNationalId(@PathVariable String nationalId) {
        return ResponseEntity.ok(applicantService.getByNationalId(nationalId));
    }

    @GetMapping("/email/{email}")
    @Operation(summary = "Get an applicant by email")
    public ResponseEntity<Applicant> getByEmail(@PathVariable String email) {
        return ResponseEntity.ok(applicantService.getByEmail(email));
    }

    @PostMapping("/{id}/deactivate")
    @Operation(summary = "Deactivate an applicant")
    public ResponseEntity<Void> deactivate(@PathVariable Long id) {
        applicantService.deactivate(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/activate")
    @Operation(summary = "Activate an applicant")
    public ResponseEntity<Void> activate(@PathVariable Long id) {
        applicantService.activate(id);
        return ResponseEntity.ok().build();
    }

    private Applicant mapToEntity(CreateApplicantRequest request) {
        Applicant applicant = new Applicant();
        applicant.setFirstName(request.getFirstName());
        applicant.setLastName(request.getLastName());
        applicant.setNationalId(request.getNationalId());
        applicant.setBirthDate(request.getBirthDate());
        applicant.setEmail(request.getEmail());
        applicant.setPhoneNumber(request.getPhoneNumber());
        applicant.setAddress(request.getAddress());
        applicant.setEducationLevel(request.getEducationLevel());
        applicant.setWorkExperience(request.getWorkExperience());
        return applicant;
    }
} 