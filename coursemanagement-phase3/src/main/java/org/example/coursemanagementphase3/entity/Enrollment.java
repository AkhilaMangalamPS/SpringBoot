package org.example.coursemanagementphase3.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

/**
 * Entity representing Enrollments in the system
 * This maps the relationship between Student and a course.
 */
@Entity
@Table(name = "enrollments")
public class Enrollment {
    /**
     * Primary key for the Enrollment entity
     * Auto-generated using IDENTITY strategy
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The date on which the student enrolled in the course
     */
    @NotNull(message = "Enrollment date is required")
    private LocalDate enrollmentDate;

    /**
     * Reference to the associated student
     *
     * Many enrollments can belong to one student
     * FetchType. LAZY is used to improve perfoemance by loaidng teh student entity only when needed
     */
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    /**
     * Reference associated with the course
     * Many enrollments can belong to one course
     */
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    /**
     *Returns the unique ID of the enrollment
     *
     * @return enwollment ID
     */
    public Long getId(){
        return id;
    }

    /**
     * Sets the unique ID of the enrollment
     *
     * @param id enrollment ID
     */
    public void setId(Long id){
        this.id = id;
    }

    /**
     * Returns the enrollment date
     *
     * @return enrollment date
     */
    public LocalDate getEnrollmentDate(){
        return enrollmentDate;
    }

    /**
     * Sets the enrollment date
     * @param enrollmentDate Enrollment date
     */
    public void setEnrollmentDate(LocalDate enrollmentDate){
        this.enrollmentDate = enrollmentDate;
    }

    /**
     * Returns the associated student
     * @return Student entity
     */
    public Student getStudent(){
        return student;
    }

    /**
     * Sets the associated student
     * @param student student entity
     */
    public void setStudent(Student student){
        this.student = student;
    }

    /**
     * Returns the associated course
     * @return Course entity
     */
    public Course getCourse(){

        return course;
    }

    /**
     * Sets the associated course
     * @param course course entity
     */
    public void setCourse(Course course){
        this.course = course;
    }







}
