package com.example.bootcampprojectcqrs.business.dto.requests.applicant;

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
public class CreateApplicantRequest {
    @NotBlank(message = "Ad alanı boş bırakılamaz")
    @Size(min = 2, max = 50, message = "Ad 2-50 karakter arasında olmalıdır")
    private String firstName;

    @NotBlank(message = "Soyad alanı boş bırakılamaz")
    @Size(min = 2, max = 50, message = "Soyad 2-50 karakter arasında olmalıdır")
    private String lastName;

    @NotBlank(message = "TC Kimlik No alanı boş bırakılamaz")
    @Pattern(regexp = "^[1-9][0-9]{10}$", message = "Geçerli bir TC Kimlik No giriniz")
    private String nationalId;

    @NotNull(message = "Doğum tarihi alanı boş bırakılamaz")
    @Past(message = "Doğum tarihi geçmiş bir tarih olmalıdır")
    private LocalDate birthDate;

    @NotBlank(message = "E-posta alanı boş bırakılamaz")
    @Email(message = "Geçerli bir e-posta adresi giriniz")
    private String email;

    @NotBlank(message = "Telefon numarası alanı boş bırakılamaz")
    @Pattern(regexp = "^[0-9]{10}$", message = "Geçerli bir telefon numarası giriniz (10 haneli)")
    private String phoneNumber;

    @NotBlank(message = "Adres alanı boş bırakılamaz")
    @Size(min = 10, max = 200, message = "Adres 10-200 karakter arasında olmalıdır")
    private String address;

    @NotBlank(message = "Eğitim seviyesi alanı boş bırakılamaz")
    private String educationLevel;

    @NotBlank(message = "İş deneyimi alanı boş bırakılamaz")
    private String workExperience;

    @NotBlank(message = "Yetenekler alanı boş bırakılamaz")
    private String skills;

    @NotBlank(message = "GitHub URL alanı boş bırakılamaz")
    @Pattern(regexp = "^https://github\\.com/[a-zA-Z0-9-]+$", message = "Geçerli bir GitHub URL'si giriniz")
    private String githubUrl;

    @NotBlank(message = "LinkedIn URL alanı boş bırakılamaz")
    @Pattern(regexp = "^https://linkedin\\.com/in/[a-zA-Z0-9-]+$", message = "Geçerli bir LinkedIn URL'si giriniz")
    private String linkedinUrl;
} 