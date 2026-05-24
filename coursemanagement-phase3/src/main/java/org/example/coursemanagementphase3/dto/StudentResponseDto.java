package org.example.coursemanagementphase3.dto;

/**
 * Data Transfer Object (DTO) for returning student data.
 * This DTo is used to student information from the server to the client API responses.
 *
 * @param id Unique ID of the student
 * @param name Name of the student
 * @param email Email of the student
 */
public record StudentResponseDto (
        Long id,
        String name,
        String email
)
{}
