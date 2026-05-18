package org.example.coursemanagementphase3.repository;

import org.example.coursemanagementphase3.entity.Course;
import org.example.coursemanagementphase3.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
