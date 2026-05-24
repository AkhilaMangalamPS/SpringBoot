package org.example.coursemanagementphase3.dto;

import java.util.List;

/**
 * Data Transfer Object (DTO) representing student along with their course
 *
 * @param id Unique identifier of the student
 * @param name Name of the student
 * @param email Email of the student
 * @param courses Courses enrolled by the student
 */
public record StudentWithCoursesDto(
        Long id,
        String name,
        String email,
        List<CourseDto> courses

){}
