package org.example.courseenrollmentsystem.repository;

import org.example.courseenrollmentsystem.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
