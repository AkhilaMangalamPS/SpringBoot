package org.example.coursemanagementphase3.dto;
import java.time.LocalDate;

/**
 * Data Transfer Object (DTO) for enrollment.
 * This DTO is used ofr transfer enrollment details between layers without exposing its internal structure
 * @param id Unique ID of the enrollment
 * @param enrollmentDate The date which the course enrolled
 * @param studentId ID of the student who enrolled
 * @param courseId ID of the course enrolled
 */
public record EnrollmentDto(
        Long id,
        LocalDate enrollmentDate,
        Long studentId,
        Long courseId


) {}
