package org.example.coursemanagementphase3.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.BatchSize;

import java.util.ArrayList;
import java.util.List;

/**
 * Entity representing student in the system
 * This maps the relationship between Course and enrollment
 */
@Entity
@Table(name = "students")
public class Student {
    /**
     * Primary key of student entity
     * Auto-generated using IDENTITY strategy
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Name of the student
     * - Name cannot be null or empty
     */
    @NotBlank(message = "Name cannot be empty")
    private String name;

    /**
     * email of the student
     * -email must be in a valid format
     */
    @Email(message = "Invalid email")
    private String email;

    /**
     * Enrollment list of the student
     * One student can enroll many courses
     * FetchType.LAZY is used to improve performance
     */
    @JsonIgnore
    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    @BatchSize(size = 10)
    private List<Enrollment> enrollments = new ArrayList<>();

    //Getters and setters

    /**
     * To get the Unique Identifier of the student
     * @return ID of the student
     */
    public Long getId(){
        return id;
    }

    /**
     * Sets the unique identifier of the student
     * @param id ID of the student
     */
    public void setId(Long id){
        this.id = id;
    }

    /**
     * To retrieve the name of the student
     * @return Name of the student
     */
    public String getName(){
        return name;
    }

    /**
     * Sets the name of the student
     * @param name Name of the student
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Return email of student
     * @return email of student
     */
    public String getEmail(){
        return email;
    }

    /**
     * Sets email of student
     * @param email email of student
     */
    public void setEmail(String email){
        this.email = email;
    }

    /**
     * Gets the enrollment list of student
     * @return enrollment list
     */
    public List<Enrollment> getEnrollments(){
        return enrollments;
    }

    /**
     * Sets the enrollments
     * @param enrollments List of enrollments
     */
    public void setEnrollments(List<Enrollment> enrollments){
        this.enrollments = enrollments;
    }

}
