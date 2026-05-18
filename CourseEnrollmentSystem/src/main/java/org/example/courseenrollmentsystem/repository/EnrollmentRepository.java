package org.example.courseenrollmentsystem.repository;

import org.example.courseenrollmentsystem.entity.Course;
import org.example.courseenrollmentsystem.entity.Enrollment;
import org.example.courseenrollmentsystem.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    boolean existsByStudentAndCourse(Student student, Course course);
    long countByCourse(Course course);
}
