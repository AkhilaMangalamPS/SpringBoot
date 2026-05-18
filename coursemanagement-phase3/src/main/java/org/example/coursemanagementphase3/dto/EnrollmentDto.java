package org.example.coursemanagementphase3.dto;
import java.time.LocalDate;

public record EnrollmentDto(
        Long id,
        LocalDate enrollmentDate,
        Long studentId,
        Long courseId


) {}
