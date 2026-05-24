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

/**
 * Service implementation for handling Student-related business logic.
 *
 * Responsibilities:
 * - Managing student creation
 * - Handling student-course enrollment
 * - Fetching student data (basic, with courses, optimized graph)
 * - Providing pagination support
 *
 * Acts as a bridge between Controller and Repository layers.
 */
@Service
public class StudentServiceImplementation implements StudentService {

    /**
     * Repository for Student entity operations.
     */
    private final StudentRepository studentRepository;

    /**
     * Repository for Course entity operations.
     */
    private final CourseRepository courseRepository;

    /**
     * Repository for Enrollment entity operations.
     */
    private final EnrollmentRepository enrollmentRepository;

    /**
     * Constructor-based dependency injection.
     */
    public StudentServiceImplementation(StudentRepository studentRepository,
                                        CourseRepository courseRepository,
                                        EnrollmentRepository enrollmentRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    /**
     * Creates a new student.
     *
     * Converts DTO to entity, persists it, and returns response DTO.
     *
     * @param dto incoming student request data
     * @return created student response DTO
     */
    @Override
    public StudentResponseDto createStudent(StudentRequestDto dto) {
        Student student = new Student();
        student.setName(dto.name());
        student.setEmail(dto.email());

        Student saved = studentRepository.save(student);

        return new StudentResponseDto(
                saved.getId(),
                saved.getName(),
                saved.getEmail()
        );
    }

    /**
     * Enrolls a student into a course.
     *
     * Transactional to ensure atomicity of operations.
     * Validates:
     * - Student existence
     * - Course existence
     * - Prevents duplicate enrollment
     *
     * @param studentId ID of the student
     * @param courseId  ID of the course
     * @return enrollment details DTO
     */
    @Transactional
    @Override
    public EnrollmentDto enrollStudent(Long studentId, Long courseId) {

        // Fetch student or throw exception
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException(studentId));

        // Fetch course or throw exception
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException(courseId));

        // Prevent duplicate enrollment
        if (enrollmentRepository.existsByStudentAndCourse(student, course)) {
            throw new AlreadyEnrolledException(studentId, courseId);
        }

        // Create new enrollment
        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setEnrollmentDate(LocalDate.now());

        Enrollment saved = enrollmentRepository.save(enrollment);

        return new EnrollmentDto(
                saved.getId(),
                saved.getEnrollmentDate(),
                studentId,
                courseId
        );
    }

    /**
     * Retrieves all students (basic details only).
     *
     * Note:
     * Explicit call to student.getEnrollments().size()
     * ensures lazy-loaded collections are initialized,
     * preventing LazyInitializationException.
     *
     * @return list of student response DTOs
     */
    @Override
    public List<StudentResponseDto> getAllStudents() {
        List<Student> students = studentRepository.findAllStudents();

        return students.stream()
                .map(student -> {
                    // Force initialization of lazy collection
                    student.getEnrollments().size();

                    return new StudentResponseDto(
                            student.getId(),
                            student.getName(),
                            student.getEmail()
                    );
                })
                .toList();
    }

    /**
     * Retrieves students along with their enrolled courses.
     *
     * Typically uses JOIN queries for efficient fetching.
     *
     * @return list of students with courses DTO
     */
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

    /**
     * Retrieves students with courses using EntityGraph optimization.
     *
     * Helps avoid N+1 query problem by fetching associations eagerly
     * in a controlled manner.
     *
     * @return optimized list of students with courses
     */
    @Override
    public List<StudentWithCoursesDto> getAllStudentsWithGraph() {
        List<Student> students = studentRepository.findAllWithGraph();

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

    /**
     * Retrieves students with pagination support.
     *
     * Uses Spring Data Pageable abstraction.
     *
     * @param page page number (0-based)
     * @param size number of records per page
     * @return paginated student response DTOs
     */
    @Override
    public Page<StudentResponseDto> getStudentsPaginated(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        Page<Student> studentPage = studentRepository.findAll(pageable);

        return studentPage.map(student ->
                new StudentResponseDto(
                        student.getId(),
                        student.getName(),
                        student.getEmail()
                )
        );
    }
}