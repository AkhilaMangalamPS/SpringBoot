package org.example.coursemanagementphase3.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record StudentRequestDto (
        @NotBlank(message = "Name is required")
        @Size(min = 3, max= 30 , message = "Characters of name must be between 3 and 30")
        String name,

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email")
        String email
){}
