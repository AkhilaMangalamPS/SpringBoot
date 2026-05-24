package org.example.coursemanagementphase3.dto;

/**
 * Data Transfer Object (DTO) for course
 * This DTO is used to transfer course data between layers (Controller <-> Service) without exposing the internal structure
 *
 * @param id Unique identifier of the course
 * @param title Title of the course
 * @param category Category or domain of the course
 */
public record CourseDto (
        Long id,
        String title,
        String category
)
{}
