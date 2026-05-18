package org.example.coursemanagementphase3.service;

import org.example.coursemanagementphase3.dto.EnrollmentDto;
import org.example.coursemanagementphase3.dto.StudentRequestDto;
import org.example.coursemanagementphase3.dto.StudentResponseDto;
import org.example.coursemanagementphase3.dto.StudentWithCoursesDto;
import org.example.coursemanagementphase3.entity.Student;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StudentService {
    StudentResponseDto createStudent(StudentRequestDto dto);
    EnrollmentDto enrollStudent(Long studentId, Long courseId);
    List<StudentResponseDto> getAllStudents();
    List<StudentWithCoursesDto> getAllStudentsWithCourses();
    List<StudentWithCoursesDto> getAllStudentsWithGraph();
    Page<StudentResponseDto> getStudentsPaginated(int page,int size);
}
