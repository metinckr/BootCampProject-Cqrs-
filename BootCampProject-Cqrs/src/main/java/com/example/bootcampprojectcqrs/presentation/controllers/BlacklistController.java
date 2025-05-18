package com.example.bootcampprojectcqrs.presentation.controllers;

import com.example.bootcampprojectcqrs.application.dtos.requests.CreateBlacklistRequest;
import com.example.bootcampprojectcqrs.application.services.BlacklistService;
import com.example.bootcampprojectcqrs.domain.entities.Blacklist;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/blacklist")
@RequiredArgsConstructor
@Tag(name = "Blacklist", description = "Blacklist management APIs")
public class BlacklistController {
    private final BlacklistService blacklistService;

    @PostMapping
    @Operation(summary = "Create a new blacklist entry")
    public ResponseEntity<Blacklist> create(@Valid @RequestBody CreateBlacklistRequest request) {
        Blacklist blacklist = mapToEntity(request);
        return new ResponseEntity<>(blacklistService.create(blacklist), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing blacklist entry")
    public ResponseEntity<Blacklist> update(@PathVariable Long id, @Valid @RequestBody CreateBlacklistRequest request) {
        Blacklist blacklist = mapToEntity(request);
        return ResponseEntity.ok(blacklistService.update(id, blacklist));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a blacklist entry")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        blacklistService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a blacklist entry by ID")
    public ResponseEntity<Blacklist> getById(@PathVariable Long id) {
        return ResponseEntity.ok(blacklistService.getById(id));
    }

    @GetMapping
    @Operation(summary = "Get all blacklist entries")
    public ResponseEntity<List<Blacklist>> getAll() {
        return ResponseEntity.ok(blacklistService.getAll());
    }

    @GetMapping("/applicant/{applicantId}")
    @Operation(summary = "Get blacklist entries by applicant ID")
    public ResponseEntity<List<Blacklist>> getBlacklistByApplicant(@PathVariable Long applicantId) {
        return ResponseEntity.ok(blacklistService.getBlacklistByApplicant(applicantId));
    }

    @GetMapping("/active")
    @Operation(summary = "Get all active blacklist entries")
    public ResponseEntity<List<Blacklist>> getActiveBlacklistEntries() {
        return ResponseEntity.ok(blacklistService.getActiveBlacklistEntries());
    }

    @GetMapping("/check/{applicantId}")
    @Operation(summary = "Check if an applicant is blacklisted")
    public ResponseEntity<Boolean> isApplicantBlacklisted(@PathVariable Long applicantId) {
        return ResponseEntity.ok(blacklistService.isApplicantBlacklisted(applicantId));
    }

    @PostMapping("/{id}/deactivate")
    @Operation(summary = "Deactivate a blacklist entry")
    public ResponseEntity<Void> deactivate(@PathVariable Long id) {
        blacklistService.deactivate(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/activate")
    @Operation(summary = "Activate a blacklist entry")
    public ResponseEntity<Void> activate(@PathVariable Long id) {
        blacklistService.activate(id);
        return ResponseEntity.ok().build();
    }

    private Blacklist mapToEntity(CreateBlacklistRequest request) {
        Blacklist blacklist = new Blacklist();
        blacklist.setApplicant(request.getApplicant());
        blacklist.setReason(request.getReason());
        blacklist.setBlacklistDate(request.getBlacklistDate());
        blacklist.setExpiryDate(request.getExpiryDate());
        blacklist.setNotes(request.getNotes());
        return blacklist;
    }
} 