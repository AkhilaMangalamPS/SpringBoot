package org.example.coursemanagementphase3.dto;

import java.util.List;

public record StudentWithCoursesDto(
        Long id,
        String name,
        String email,
        List<CourseDto> courses

){}
