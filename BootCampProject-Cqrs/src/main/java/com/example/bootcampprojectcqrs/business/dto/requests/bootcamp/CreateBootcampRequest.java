package com.example.bootcampprojectcqrs.business.dto.requests.bootcamp;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateBootcampRequest {
    @NotBlank(message = "Bootcamp adı boş bırakılamaz")
    @Size(min = 3, max = 100, message = "Bootcamp adı 3-100 karakter arasında olmalıdır")
    private String name;

    @NotBlank(message = "Açıklama alanı boş bırakılamaz")
    @Size(min = 10, max = 500, message = "Açıklama 10-500 karakter arasında olmalıdır")
    private String description;

    @NotNull(message = "Başlangıç tarihi boş bırakılamaz")
    @Future(message = "Başlangıç tarihi gelecek bir tarih olmalıdır")
    private LocalDate startDate;

    @NotNull(message = "Bitiş tarihi boş bırakılamaz")
    @Future(message = "Bitiş tarihi gelecek bir tarih olmalıdır")
    private LocalDate endDate;

    @NotNull(message = "Kapasite boş bırakılamaz")
    @Min(value = 1, message = "Kapasite en az 1 olmalıdır")
    @Max(value = 100, message = "Kapasite en fazla 100 olabilir")
    private Integer capacity;

    @NotBlank(message = "Teknoloji alanı boş bırakılamaz")
    private String technology;

    @NotBlank(message = "Seviye alanı boş bırakılamaz")
    @Pattern(regexp = "^(BEGINNER|INTERMEDIATE|ADVANCED)$", message = "Geçerli bir seviye giriniz (BEGINNER, INTERMEDIATE, ADVANCED)")
    private String level;

    @NotBlank(message = "Konum alanı boş bırakılamaz")
    private String location;

    @NotNull(message = "Eğitmen ID boş bırakılamaz")
    private Long instructorId;
} 