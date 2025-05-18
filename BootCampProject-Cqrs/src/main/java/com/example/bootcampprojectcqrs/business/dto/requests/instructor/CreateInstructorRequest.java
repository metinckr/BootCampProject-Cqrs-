package com.example.bootcampprojectcqrs.business.dto.requests.instructor;

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
    @NotBlank(message = "Ad alanı boş bırakılamaz")
    @Size(min = 2, max = 50, message = "Ad 2-50 karakter arasında olmalıdır")
    private String firstName;

    @NotBlank(message = "Soyad alanı boş bırakılamaz")
    @Size(min = 2, max = 50, message = "Soyad 2-50 karakter arasında olmalıdır")
    private String lastName;

    @NotBlank(message = "E-posta alanı boş bırakılamaz")
    @Email(message = "Geçerli bir e-posta adresi giriniz")
    private String email;

    @NotBlank(message = "Telefon numarası alanı boş bırakılamaz")
    @Pattern(regexp = "^[0-9]{10}$", message = "Geçerli bir telefon numarası giriniz (10 haneli)")
    private String phoneNumber;

    @NotBlank(message = "Uzmanlık alanı boş bırakılamaz")
    @Size(min = 3, max = 100, message = "Uzmanlık alanı 3-100 karakter arasında olmalıdır")
    private String expertise;

    @NotBlank(message = "Biyografi alanı boş bırakılamaz")
    @Size(min = 50, max = 1000, message = "Biyografi 50-1000 karakter arasında olmalıdır")
    private String bio;

    @NotBlank(message = "LinkedIn URL alanı boş bırakılamaz")
    @Pattern(regexp = "^https://linkedin\\.com/in/[a-zA-Z0-9-]+$", message = "Geçerli bir LinkedIn URL'si giriniz")
    private String linkedinUrl;

    @NotBlank(message = "GitHub URL alanı boş bırakılamaz")
    @Pattern(regexp = "^https://github\\.com/[a-zA-Z0-9-]+$", message = "Geçerli bir GitHub URL'si giriniz")
    private String githubUrl;
} 