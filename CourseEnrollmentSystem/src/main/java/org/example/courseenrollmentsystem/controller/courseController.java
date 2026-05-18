package org.example.courseenrollmentsystem.controller;

import org.example.courseenrollmentsystem.dto.CourseDto;
import org.example.courseenrollmentsystem.entity.Course;
import org.example.courseenrollmentsystem.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/course")
public class courseController {
    @Autowired
    private CourseRepository courseRepository;

    @PostMapping
    public Course createCourse(@RequestBody CourseDto dto){
        Course course = new Course();
        course.setTitle(dto.title());
        course.setDepartment(dto.department());
        course.setCapacity(dto.capacity());
        return courseRepository.save(course);
    }
}
