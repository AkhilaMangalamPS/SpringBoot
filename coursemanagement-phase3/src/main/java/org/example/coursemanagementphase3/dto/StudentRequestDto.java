package org.example.coursemanagementphase3.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Data Request Object(DTO) for creating a student
 *
 * @param name Name of the student
 *             - Must not be null or empty
 *             - Length must be between 3 and 30 chracaters
 * @param email Email of the student
 *              - Must not be null or empty
 *              - Must follow a valid email format
 */
public record StudentRequestDto (
        @NotBlank(message = "Name is required")
        @Size(min = 3, max= 30 , message = "Characters of name must be between 3 and 30")
        String name,

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email")
        String email
){}
