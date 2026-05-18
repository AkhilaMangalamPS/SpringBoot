package org.example.courseenrollmentsystem.repository;

import org.example.courseenrollmentsystem.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
