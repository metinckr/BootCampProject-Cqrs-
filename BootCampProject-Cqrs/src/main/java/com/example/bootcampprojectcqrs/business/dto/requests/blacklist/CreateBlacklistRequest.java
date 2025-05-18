package com.example.bootcampprojectcqrs.business.dto.requests.blacklist;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateBlacklistRequest {
    @NotNull(message = "Başvuru sahibi ID boş bırakılamaz")
    private Long applicantId;

    @NotBlank(message = "Neden alanı boş bırakılamaz")
    private String reason;

    @NotNull(message = "Kara liste başlangıç tarihi boş bırakılamaz")
    private LocalDateTime blacklistDate;

    @NotNull(message = "Kara liste bitiş tarihi boş bırakılamaz")
    @Future(message = "Kara liste bitiş tarihi gelecek bir tarih olmalıdır")
    private LocalDateTime expiryDate;

    private String notes;
} 