package org.example.coursemanagementphase3.controller;

import org.example.coursemanagementphase3.dto.CourseDto;
import org.example.coursemanagementphase3.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<CourseDto> createCourse(@RequestBody CourseDto dto){
        CourseDto response = courseService.createCourse(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @GetMapping
    public ResponseEntity<List<CourseDto>> getAllCourses(){
        List<CourseDto> response = courseService.getAllCourses();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
