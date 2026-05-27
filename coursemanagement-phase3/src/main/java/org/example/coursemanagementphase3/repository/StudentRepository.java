package org.example.coursemanagementphase3.repository;

import org.example.coursemanagementphase3.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByNameContaining(String name);
    Page<Student> findAll(Pageable pageable);

    @Query("SELECT s FROM Student s")
    List<Student> findAllStudents();

    @Query("""
    SELECT s FROM Student s
    JOIN FETCH s.enrollments e
    JOIN FETCH e.course""")
    List<Student> findAllWithCourses();

    @EntityGraph(attributePaths = {"enrollments", "enrollments.course"})
    @Query("SELECT s FROM Student s")
    List<Student> findAllWithGraph();

    Optional<Student> findByEmail(String email);

}
