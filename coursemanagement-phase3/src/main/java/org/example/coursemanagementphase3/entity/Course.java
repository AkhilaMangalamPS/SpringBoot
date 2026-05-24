package org.example.coursemanagementphase3.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.BatchSize;

import java.util.ArrayList;
import java.util.List;
/**
 * Entity representing course in the system.
 */
@Entity
@Table(name = "courses")
public class Course {
    /**
    *Primary key for the Course Entity
    *Auto generated using IDENTITY strategy
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
    *Title of the course
    *Cannot be null or empty
     */
    @NotBlank(message = "Course name must be required")
    private String title;

    /**
     * Category of the course
     * Cannot be null
     */
    @NotBlank(message = "Category is required")
    private String category;

    /**
     * List of enrollments associated with the course
     *
     * -mappedBy = "course"  -> Ownership is on Enrollment entity
     * -FetchType.LAZY -> Avoid loaidng enrollments unless needed
     * @JsonIgnore - Prevent infinte recursion in REST responses
     */
    @JsonIgnore
    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    @BatchSize(size = 10)
    private List<Enrollment> enrollments = new ArrayList<>();

    //Getters and setters

    /**
     *
     * @return courseID
     */
    public Long getId(){
        return id;
    }

    /**
     *
     * @param id set courseID
     */
    public void setId(Long id){
        this.id = id;
    }

    /**
     *
     * @return title
     */
    public String getTitle(){
        return title;
    }

    /**
     *
     * @param title Title of the course
     */
    public void setTitle(String title){
        this.title =title;
    }

    /**
     *
     * @return category of the course
     */
    public String getCategory(){
        return category;
    }

    /**
     *
     * @param category Category of the course
     */
    public void setCategory(String category){
        this.category = category;
    }

    /**
     *
     * @return List of enrollments
     */
    public List<Enrollment> getEnrollments(){
        return enrollments;
    }

    /**
     *
     * @param enrollments List of enrollment
     */
    public void setEnrollments(List<Enrollment> enrollments){
        this.enrollments = enrollments;
    }



}
