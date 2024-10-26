package org.example.taskmanagementsystemproject.dto.request.user;

import jakarta.validation.constraints.NotBlank;

public record LoginRequestDto(
        @NotBlank
        String username,

        @NotBlank
        String password
) {

}
