package org.example.coursemanagementphase3.service;

import org.example.coursemanagementphase3.dto.EnrollmentDto;
import org.example.coursemanagementphase3.entity.Course;
import org.example.coursemanagementphase3.entity.Enrollment;
import org.example.coursemanagementphase3.entity.Student;
import org.example.coursemanagementphase3.exception.AlreadyEnrolledException;
import org.example.coursemanagementphase3.exception.CourseNotFoundException;
import org.example.coursemanagementphase3.exception.StudentNotFoundException;
import org.example.coursemanagementphase3.repository.CourseRepository;
import org.example.coursemanagementphase3.repository.EnrollmentRepository;
import org.example.coursemanagementphase3.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;
    @Mock
    private CourseRepository courseRepository;
    @Mock
    private EnrollmentRepository enrollmentRepository;

    @InjectMocks
    private StudentServiceImplementation studentService;

    @Test
    void testEnrollSuccess(){
        Student student = new Student();
        student.setId(1L);

        Course course = new Course();
        course.setId(1L);

        Enrollment enrollment = new Enrollment();
        enrollment.setId(1L);
        enrollment.setStudent(student);
        enrollment.setCourse(course);

        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
        when(enrollmentRepository.existsByStudentAndCourse(student,course)).thenReturn(false);
        when(enrollmentRepository.save(any(Enrollment.class))).thenReturn(enrollment);

        EnrollmentDto result = studentService.enrollStudent(1L,1L);

        assertEquals(1L,result.studentId());
        assertEquals(1L,result.courseId());
    }

    @Test
    void testEnrollStudent_StudentNotFound(){
        when(studentRepository.findById(1L))
                .thenReturn(Optional.empty());
        assertThrows(StudentNotFoundException.class,
                () -> studentService.enrollStudent(1L,1L));

    }

    @Test
    void testEnrollStudent_CourseNotFound(){
        Student student = new Student();
        student.setId(1L);

        when(studentRepository.findById(1L))
                .thenReturn(Optional.of(student));

        when(courseRepository.findById(1L))
                .thenReturn(Optional.empty());
        assertThrows(CourseNotFoundException.class,
                () -> studentService.enrollStudent(1L,1L));

    }

    @Test
    void testEnrollStudent_AlreadyEnrolled(){
        Student student = new Student();
        student.setId(1L);

        Course course = new Course();
        course.setId(1L);

        when(studentRepository.findById(1L))
                .thenReturn(Optional.of(student));

        when(courseRepository.findById(1L))
                .thenReturn(Optional.of(course));

        when(enrollmentRepository.existsByStudentAndCourse(student,course))
                .thenReturn(true);

        assertThrows(AlreadyEnrolledException.class,
                () -> studentService.enrollStudent(1L,1L));

    }

    @Test
    void testEnrollStudent_StudentNotFound_verifyNoFurtherCalls(){
        when(studentRepository.findById(1L))
                .thenReturn(Optional.empty());
        assertThrows(StudentNotFoundException.class,
                () -> studentService.enrollStudent(1L,1L));
        verify(courseRepository, never()).findById(anyLong());
        verify(enrollmentRepository, never()).save(any());
    }

    @Test
    void testEnrollStudent_CourseNotFound_verifyNoFurtherCalls(){
        Student student = new Student();
        student.setId(1L);

        when(studentRepository.findById(1L))
                .thenReturn(Optional.of(student));

        when(courseRepository.findById(1L))
                .thenReturn(Optional.empty());
        assertThrows(CourseNotFoundException.class,
                () -> studentService.enrollStudent(1L,1L));
        verify(studentRepository, times(1)).findById(1L);
        verify(courseRepository, times(1)).findById(1L);
        verify(enrollmentRepository, never()).save(any());
    }
}
