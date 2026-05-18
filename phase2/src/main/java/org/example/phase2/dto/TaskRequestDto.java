package org.example.phase2.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record TaskRequestDto (
        @NotBlank(message = "Title cannot be empty")
        String title,

        @NotNull(message = "Completed status is required")
        boolean status
){}
