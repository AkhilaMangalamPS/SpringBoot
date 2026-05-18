package org.example.courseenrollmentsystem.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record StudentDto(
        @NotBlank(message = "Name is required")
        @Size(min = 3, max = 30, message = "Name must be between 3 and 30 characters")
        String name,

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email")
        String email
      )
{}



