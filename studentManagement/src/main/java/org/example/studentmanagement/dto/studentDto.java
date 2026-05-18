package org.example.studentmanagement.dto;

import jakarta.validation.constraints.*;
import org.example.studentmanagement.validation.ValidStudentEmail;

public record studentDto(
    @NotBlank(message = "Name cannot be empty")
    @Size(min = 3, max = 30)
    String name,

    @Email(message = "Invalid email format")
    @ValidStudentEmail
    String email,

    @Min(value = 18, message = "Minimum age is 18")
    @Max(value = 60, message = "Maximum age is 60")
    int age
) {}
