package com.example.bootcampprojectcqrs.business.dto.requests.application;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateApplicationRequest {
    @NotNull(message = "Başvuru sahibi ID boş bırakılamaz")
    private Long applicantId;

    @NotNull(message = "Bootcamp ID boş bırakılamaz")
    private Long bootcampId;

    private String notes;
} 