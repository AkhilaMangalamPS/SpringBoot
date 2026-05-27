package org.example.coursemanagementphase3.controller;

import org.example.coursemanagementphase3.dto.CourseDto;
import org.example.coursemanagementphase3.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * Rest Controller for managing Course-related operations
 * Endpoints for create and retrieve courses
 */
@RestController
@RequestMapping("/course")
public class CourseController {
    /**
     * Service layer dependency for handling course operations
     */
    private final CourseService courseService;

    /**
     * Constructor based dependency injection for CourseService
     * @param courseService service responsible for course operations
     */
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    /**
     * Endpoint to create new course
     *
     * Accepts course details in the request body and delegates creation to the service layer , and returns the created course
     * @param dto Course data transfer object containing course details
     * @return Response entity containing created courseDto and Http Status
     */
    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('ADMIN','FACULTY')")
    public ResponseEntity<CourseDto> createCourse(@RequestBody CourseDto dto){
        CourseDto response = courseService.createCourse(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    /**
     * Endpoint to get all courses
     *
     * Fetches the list of all available courses from the service layer
     * @return Response entity contains List of CourseDto and Http status
     */
    @GetMapping("/all")
    @PreAuthorize("hasAnyRle('ADMIN','FACULTY','STUDENT')")
    public ResponseEntity<List<CourseDto>> getAllCourses(){
        List<CourseDto> response = courseService.getAllCourses();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
