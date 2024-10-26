package org.example.taskmanagementsystemproject.dto.request.user;

import jakarta.persistence.Column;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequestDto {

    @NotNull
    @NotEmpty
    String name;

    @NotNull
    @NotEmpty
    String surname;

    @Email
    String email;

    @NotNull
    @Column(unique = true)
    String username;

    @NotBlank
    @Pattern(message = "Lütfen şifre kurallarına uyunuz",
            regexp = "^(?=.*[A-Za-z])(?=.*\\d).+$")
    String password;
}
