package org.example.coursemanagementphase3.service;

import jakarta.transaction.Transactional;
import org.example.coursemanagementphase3.dto.*;
import org.example.coursemanagementphase3.entity.Course;
import org.example.coursemanagementphase3.entity.Enrollment;
import org.example.coursemanagementphase3.entity.Student;
import org.example.coursemanagementphase3.exception.AlreadyEnrolledException;
import org.example.coursemanagementphase3.exception.CourseNotFoundException;
import org.example.coursemanagementphase3.exception.StudentNotFoundException;
import org.example.coursemanagementphase3.repository.CourseRepository;
import org.example.coursemanagementphase3.repository.EnrollmentRepository;
import org.example.coursemanagementphase3.repository.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class StudentServiceImplementation implements StudentService {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final EnrollmentRepository enrollmentRepository;

    public StudentServiceImplementation(StudentRepository studentRepository, CourseRepository courseRepository, EnrollmentRepository enrollmentRepository){
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.enrollmentRepository = enrollmentRepository;
    }


    @Override
    public StudentResponseDto createStudent(StudentRequestDto dto) {
        Student student = new Student();
        student.setName(dto.name());
        student.setEmail(dto.email());
        Student saved = studentRepository.save(student);
        return new StudentResponseDto(saved.getId(),saved.getName(),saved.getEmail());
    }

    @Transactional
    @Override
    public EnrollmentDto enrollStudent(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new StudentNotFoundException(studentId));
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new CourseNotFoundException(courseId));
        if(enrollmentRepository.existsByStudentAndCourse(student,course)){
            throw new AlreadyEnrolledException(studentId,courseId);
        }
        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setEnrollmentDate(LocalDate.now());
        Enrollment saved = enrollmentRepository.save(enrollment);
        return new EnrollmentDto(saved.getId(),saved.getEnrollmentDate(),studentId,courseId);


    }

    @Override
    public List<StudentResponseDto> getAllStudents() {
        List<Student> students = studentRepository.findAllStudents();
        return students.stream()
                .map(student -> {
                    student.getEnrollments().size();

                    return new StudentResponseDto(
                            student.getId(),
                            student.getName(),
                            student.getEmail()
                    );
                })
                .toList();
    }

    @Override
    public List<StudentWithCoursesDto> getAllStudentsWithCourses() {
        List<Student> students = studentRepository.findAllWithCourses();
        return students.stream()
                .map(student -> {
                    List<CourseDto> courses = student.getEnrollments().stream()
                            .map(enrollment -> {
                                Course course = enrollment.getCourse();
                                return new CourseDto(
                                        course.getId(),
                                        course.getTitle(),
                                        course.getCategory()
                                );
                            })
                            .toList();
                    return new StudentWithCoursesDto(
                            student.getId(),
                            student.getName(),
                            student.getEmail(),
                            courses
                    );
                })
                .toList();

    }

    @Override
    public List<StudentWithCoursesDto> getAllStudentsWithGraph() {
        List<Student> students= studentRepository.findAllWithGraph();
        return students.stream()
                .map(student -> {
                    List<CourseDto>  courses = student.getEnrollments().stream()
                            .map(enrollment -> {
                                Course course = enrollment.getCourse();
                                return new CourseDto(
                                        course.getId(),
                                        course.getTitle(),
                                        course.getCategory()
                                );
                            })
                            .toList();
                    return new StudentWithCoursesDto(
                            student.getId(),
                            student.getName(),
                            student.getEmail(),
                            courses
                    );
                })
                .toList();

    }

    @Override
    public Page<StudentResponseDto> getStudentsPaginated(int page,int size){
        Pageable pageable = PageRequest.of(page,size);
        Page<Student> studentPage = studentRepository.findAll(pageable);
        return studentPage.map(student ->
                new StudentResponseDto(
                        student.getId(),
                        student.getName(),
                        student.getEmail()
                ));

    }
}
