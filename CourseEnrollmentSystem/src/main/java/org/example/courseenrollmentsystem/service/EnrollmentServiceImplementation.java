package org.example.courseenrollmentsystem.service;

import org.example.courseenrollmentsystem.dto.EnrollmentRequestDto;
import org.example.courseenrollmentsystem.dto.EnrollmentResponseDto;
import org.example.courseenrollmentsystem.entity.Course;
import org.example.courseenrollmentsystem.entity.Enrollment;
import org.example.courseenrollmentsystem.entity.Student;
import org.example.courseenrollmentsystem.exception.ResourceNotFoundException;
import org.example.courseenrollmentsystem.repository.CourseRepository;
import org.example.courseenrollmentsystem.repository.EnrollmentRepository;
import org.example.courseenrollmentsystem.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentServiceImplementation implements EnrollmentService {
    private final EnrollmentRepository enrollmentRepo;
    private final StudentRepository studentRepo;
    private final CourseRepository courseRepo;

    public EnrollmentServiceImplementation(EnrollmentRepository repo, EnrollmentRepository enrollmentRepo, StudentRepository studentRepo, CourseRepository courseRepo) {
        this.enrollmentRepo = enrollmentRepo;
        this.studentRepo = studentRepo;
        this.courseRepo = courseRepo;
    }


    @Override
    public EnrollmentResponseDto enroll(EnrollmentRequestDto dto) {
        Long studentId = dto.studentId();
        Student student = studentRepo.findById(studentId).orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        Long courseId = dto.courseId();
        Course course = courseRepo.findById(courseId).orElseThrow(() -> new ResourceNotFoundException(("Course not found")));

        if(enrollmentRepo.existsByStudentAndCourse(student,course)){
            throw new IllegalStateException("Student already enrolled in this course");
        }
        long count = enrollmentRepo.countByCourse(course);
        if (count >= course.getCapacity()){
            throw new IllegalStateException("Course is full");
        }

        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        Enrollment saved = enrollmentRepo.save(enrollment);

        return new EnrollmentResponseDto(student.getName(),course.getTitle(),saved.getEnrollmentDate());


    }
}
