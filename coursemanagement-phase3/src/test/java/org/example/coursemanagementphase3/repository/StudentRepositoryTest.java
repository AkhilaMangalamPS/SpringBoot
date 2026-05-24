package org.example.coursemanagementphase3.repository;

import org.example.coursemanagementphase3.entity.Course;
import org.example.coursemanagementphase3.entity.Enrollment;
import org.example.coursemanagementphase3.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest
public class StudentRepositoryTest {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;
    @Test
    void testSaveAndFind(){
        Student student = new Student();
        student.setName("Akhila");
        student.setEmail("Akhila@gmail.com");
        studentRepository.save(student);

        List<Student> result = studentRepository.findByNameContaining("Akhila");
        assertEquals(1, result.size());
        assertEquals("Akhila",result.get(0).getName());
        assertEquals("Akhila@gmail.com",result.get(0).getEmail());
    }

    @Test
    void testFindAllStudents(){
        Student s1 = new Student();
        s1.setName("John");
        s1.setEmail("John@gmail.com");
        studentRepository.save(s1);

        Student s2 = new Student();
        s2.setName("Tom");

        s2.setEmail("Tom@gmail.com");
        studentRepository.save(s2);

        List<Student> result = studentRepository.findAllStudents();
        assertEquals(2,result.size());
    }

    @Test
    void testFindAllWithCourses(){
        Student s1 = new Student();
        s1.setName("Jerry");
        s1.setEmail("jerry@gmail.com");
        s1 = studentRepository.save(s1);

        Course course = new Course();
        course.setTitle("Java");
        course.setCategory("Programming");
        course = courseRepository.save(course);

        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(s1);
        enrollment.setCourse(course);
        enrollmentRepository.save(enrollment);

        List<Student> result = studentRepository.findAllWithCourses();
        assertEquals(2, result.size());
        Student fetchedStudent = result.get(0);

        assertEquals("Jerry", fetchedStudent.getName());

        assertEquals(1, fetchedStudent.getEnrollments().size());
        assertEquals("Java",fetchedStudent.getEnrollments().get(0).getCourse().getTitle());

    }
}
