package org.example.courseenrollmentsystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EnrollmentRequestDto (
    @NotNull(message = "Student id is required")
    Long studentId,

    @NotNull(message = "Course id is required")
    Long courseId
   ){}


