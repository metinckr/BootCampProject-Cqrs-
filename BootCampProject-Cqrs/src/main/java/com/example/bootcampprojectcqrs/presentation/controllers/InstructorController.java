package com.example.bootcampprojectcqrs.presentation.controllers;

import com.example.bootcampprojectcqrs.application.dtos.requests.CreateInstructorRequest;
import com.example.bootcampprojectcqrs.application.services.InstructorService;
import com.example.bootcampprojectcqrs.domain.entities.Instructor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/instructors")
@RequiredArgsConstructor
@Tag(name = "Instructor", description = "Instructor management APIs")
public class InstructorController {
    private final InstructorService instructorService;

    @PostMapping
    @Operation(summary = "Create a new instructor")
    public ResponseEntity<Instructor> create(@Valid @RequestBody CreateInstructorRequest request) {
        Instructor instructor = mapToEntity(request);
        return new ResponseEntity<>(instructorService.create(instructor), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing instructor")
    public ResponseEntity<Instructor> update(@PathVariable Long id, @Valid @RequestBody CreateInstructorRequest request) {
        Instructor instructor = mapToEntity(request);
        return ResponseEntity.ok(instructorService.update(id, instructor));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an instructor")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        instructorService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get an instructor by ID")
    public ResponseEntity<Instructor> getById(@PathVariable Long id) {
        return ResponseEntity.ok(instructorService.getById(id));
    }

    @GetMapping
    @Operation(summary = "Get all instructors")
    public ResponseEntity<List<Instructor>> getAll() {
        return ResponseEntity.ok(instructorService.getAll());
    }

    @GetMapping("/email/{email}")
    @Operation(summary = "Get an instructor by email")
    public ResponseEntity<Instructor> getByEmail(@PathVariable String email) {
        return ResponseEntity.ok(instructorService.getByEmail(email));
    }

    @GetMapping("/active")
    @Operation(summary = "Get all active instructors")
    public ResponseEntity<List<Instructor>> getActiveInstructors() {
        return ResponseEntity.ok(instructorService.getActiveInstructors());
    }

    @PostMapping("/{id}/deactivate")
    @Operation(summary = "Deactivate an instructor")
    public ResponseEntity<Void> deactivate(@PathVariable Long id) {
        instructorService.deactivate(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/activate")
    @Operation(summary = "Activate an instructor")
    public ResponseEntity<Void> activate(@PathVariable Long id) {
        instructorService.activate(id);
        return ResponseEntity.ok().build();
    }

    private Instructor mapToEntity(CreateInstructorRequest request) {
        Instructor instructor = new Instructor();
        instructor.setFirstName(request.getFirstName());
        instructor.setLastName(request.getLastName());
        instructor.setEmail(request.getEmail());
        instructor.setPhoneNumber(request.getPhoneNumber());
        instructor.setExpertise(request.getExpertise());
        instructor.setExperience(request.getExperience());
        return instructor;
    }
} 