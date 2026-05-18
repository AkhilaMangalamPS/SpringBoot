package org.example.phase3.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserDto(

        Long id,

        @NotNull(message = "Name is required")
        @Size(min = 3, max = 15, message = "Name must be between 3 and 15 characters")
        String name
) {}
