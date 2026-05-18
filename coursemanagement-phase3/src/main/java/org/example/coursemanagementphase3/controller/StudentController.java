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


@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<StudentResponseDto> createStudent( @Valid @RequestBody StudentRequestDto dto){
        StudentResponseDto response = studentService.createStudent(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/enroll")
    public ResponseEntity<EnrollmentDto> enrollStudent(@RequestParam Long studentId, @RequestParam Long courseId){
        EnrollmentDto response = studentService.enrollStudent(studentId,courseId);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<StudentResponseDto>> getAllStudents(){
        List<StudentResponseDto> response = studentService.getAllStudents();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/with-courses")
    public ResponseEntity<List<StudentWithCoursesDto>> getStudentWithCourse(){
        List<StudentWithCoursesDto> response = studentService.getAllStudentsWithCourses();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/with-graph")
    public ResponseEntity<List<StudentWithCoursesDto>> getStudentWithGraph(){
        List<StudentWithCoursesDto> response = studentService.getAllStudentsWithGraph();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/paginated")
    public ResponseEntity<Page<StudentResponseDto>> getStudentsPaginated(@RequestParam int page, @RequestParam int size){
        Page<StudentResponseDto> response = studentService.getStudentsPaginated(page,size);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
