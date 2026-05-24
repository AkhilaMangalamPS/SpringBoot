package org.example.coursemanagementphase3.controller;

import jakarta.validation.Valid;
import org.example.coursemanagementphase3.dto.EnrollmentDto;
import org.example.coursemanagementphase3.dto.StudentRequestDto;
import org.example.coursemanagementphase3.dto.StudentResponseDto;
import org.example.coursemanagementphase3.dto.StudentWithCoursesDto;

import org.example.coursemanagementphase3.service.StudentService;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Rest controller for handling student-related operations
 * Created endpoints for
 * - Create new student
 * - Enrolling student to a course
 * - Fetch all students
 * - Pagination support
 */

@RestController
@RequestMapping("/students")
public class StudentController {
    /**
     * Service layer dependency for student operations
     */
    private final StudentService studentService;

    /**
     * Constructor based dependency injection
     * @param studentService service handling student business logic
     */
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * Creates new student
     * Validates incoming data before processing
     * @param dto request DTO containing student details
     * @return created student data with Http status
     */

    @PostMapping
    public ResponseEntity<StudentResponseDto> createStudent( @Valid @RequestBody StudentRequestDto dto){
        StudentResponseDto response = studentService.createStudent(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Enroll a student into a course
     * Uses request parameter to identify course and student
     * @param studentId ID of the student
     * @param courseId ID of the course
     * @return enrollment details with Http status
     */
    @PostMapping("/enroll")
    public ResponseEntity<EnrollmentDto> enrollStudent(@RequestParam Long studentId, @RequestParam Long courseId){
        EnrollmentDto response = studentService.enrollStudent(studentId,courseId);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Endpoint to fetch all students
     * @return List of students with Http status
     */
    @GetMapping
    public ResponseEntity<List<StudentResponseDto>> getAllStudents(){
        List<StudentResponseDto> response = studentService.getAllStudents();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * Retrieves all students along with their course
     * @return List of students with their course
     */
    @GetMapping("/with-courses")
    public ResponseEntity<List<StudentWithCoursesDto>> getStudentWithCourse(){
        List<StudentWithCoursesDto> response = studentService.getAllStudentsWithCourses();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * Retrieves all students with their course using Entity Graph strategy
     * @return List of students with their course (optimized fetch)
     */
    @GetMapping("/with-graph")
    public ResponseEntity<List<StudentWithCoursesDto>> getStudentWithGraph(){
        List<StudentWithCoursesDto> response = studentService.getAllStudentsWithGraph();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * Retrieves all students with pagination support
     * @param page page number (0 based index)
     * @param size number of records per page
     * @return List of paginated student data
     */
    @GetMapping("/paginated")
    public ResponseEntity<Page<StudentResponseDto>> getStudentsPaginated(@RequestParam int page, @RequestParam int size){
        Page<StudentResponseDto> response = studentService.getStudentsPaginated(page,size);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
