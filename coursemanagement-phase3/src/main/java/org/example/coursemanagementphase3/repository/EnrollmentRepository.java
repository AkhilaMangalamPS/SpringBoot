package org.example.coursemanagementphase3.repository;

import org.example.coursemanagementphase3.entity.Course;
import org.example.coursemanagementphase3.entity.Enrollment;
import org.example.coursemanagementphase3.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    boolean existsByStudentAndCourse(Student student, Course course);


}
