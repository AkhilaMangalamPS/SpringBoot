package org.example.coursemanagementphase3.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;


@Entity
@Table(name = "enrollments")
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Enrollment date is required")
    private LocalDate enrollmentDate;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }

    public LocalDate getEnrollmentDate(){
        return enrollmentDate;
    }
    public void setEnrollmentDate(LocalDate enrollmentDate){
        this.enrollmentDate = enrollmentDate;
    }

    public Student getStudent(){
        return student;
    }
    public void setStudent(Student student){
        this.student = student;
    }

    public Course getCourse(){

        return course;
    }
    public void setCourse(Course course){
        this.course = course;
    }







}
