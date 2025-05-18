package com.example.bootcampprojectcqrs.presentation.controllers;

import com.example.bootcampprojectcqrs.application.dtos.requests.CreateBootcampRequest;
import com.example.bootcampprojectcqrs.application.services.BootcampService;
import com.example.bootcampprojectcqrs.domain.entities.Bootcamp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bootcamps")
@RequiredArgsConstructor
@Tag(name = "Bootcamp", description = "Bootcamp management APIs")
public class BootcampController {
    private final BootcampService bootcampService;

    @PostMapping
    @Operation(summary = "Create a new bootcamp")
    public ResponseEntity<Bootcamp> create(@Valid @RequestBody CreateBootcampRequest request) {
        Bootcamp bootcamp = mapToEntity(request);
        return new ResponseEntity<>(bootcampService.create(bootcamp), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing bootcamp")
    public ResponseEntity<Bootcamp> update(@PathVariable Long id, @Valid @RequestBody CreateBootcampRequest request) {
        Bootcamp bootcamp = mapToEntity(request);
        return ResponseEntity.ok(bootcampService.update(id, bootcamp));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a bootcamp")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bootcampService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a bootcamp by ID")
    public ResponseEntity<Bootcamp> getById(@PathVariable Long id) {
        return ResponseEntity.ok(bootcampService.getById(id));
    }

    @GetMapping
    @Operation(summary = "Get all bootcamps")
    public ResponseEntity<List<Bootcamp>> getAll() {
        return ResponseEntity.ok(bootcampService.getAll());
    }

    @GetMapping("/active")
    @Operation(summary = "Get all active bootcamps")
    public ResponseEntity<List<Bootcamp>> getActiveBootcamps() {
        return ResponseEntity.ok(bootcampService.getActiveBootcamps());
    }

    @GetMapping("/instructor/{instructorId}")
    @Operation(summary = "Get bootcamps by instructor ID")
    public ResponseEntity<List<Bootcamp>> getBootcampsByInstructor(@PathVariable Long instructorId) {
        return ResponseEntity.ok(bootcampService.getBootcampsByInstructor(instructorId));
    }

    @GetMapping("/status/{status}")
    @Operation(summary = "Get bootcamps by status")
    public ResponseEntity<List<Bootcamp>> getBootcampsByStatus(@PathVariable String status) {
        return ResponseEntity.ok(bootcampService.getBootcampsByStatus(status));
    }

    @PostMapping("/{id}/deactivate")
    @Operation(summary = "Deactivate a bootcamp")
    public ResponseEntity<Void> deactivate(@PathVariable Long id) {
        bootcampService.deactivate(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/activate")
    @Operation(summary = "Activate a bootcamp")
    public ResponseEntity<Void> activate(@PathVariable Long id) {
        bootcampService.activate(id);
        return ResponseEntity.ok().build();
    }

    private Bootcamp mapToEntity(CreateBootcampRequest request) {
        Bootcamp bootcamp = new Bootcamp();
        bootcamp.setName(request.getName());
        bootcamp.setDescription(request.getDescription());
        bootcamp.setStartDate(request.getStartDate());
        bootcamp.setEndDate(request.getEndDate());
        bootcamp.setCapacity(request.getCapacity());
        bootcamp.setStatus(request.getStatus());
        bootcamp.setInstructor(request.getInstructor());
        return bootcamp;
    }
} 