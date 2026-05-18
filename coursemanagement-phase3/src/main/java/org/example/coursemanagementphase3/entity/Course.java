package org.example.coursemanagementphase3.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.BatchSize;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Course name must be required")
    private String title;

    @NotBlank(message = "Category is required")
    private String category;

    @JsonIgnore
    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    @BatchSize(size = 10)
    private List<Enrollment> enrollments = new ArrayList<>();

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }

    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title =title;
    }

    public String getCategory(){
        return category;
    }
    public void setCategory(String category){
        this.category = category;
    }

    public List<Enrollment> getEnrollments(){
        return enrollments;
    }
    public void setEnrollments(List<Enrollment> enrollments){
        this.enrollments = enrollments;
    }



}
