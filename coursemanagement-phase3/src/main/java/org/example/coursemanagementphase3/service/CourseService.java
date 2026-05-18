package org.example.coursemanagementphase3.service;

import org.example.coursemanagementphase3.dto.CourseDto;

import java.util.List;

public interface CourseService {
    CourseDto createCourse(CourseDto dto);
    List<CourseDto> getAllCourses();
}
