package org.example.courseenrollmentsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String department;

    @Column(nullable = false)
    private int capacity;

    @JsonIgnore
    @OneToMany(mappedBy = "course" ,fetch = FetchType.LAZY)
    private List<Enrollment> enrollments;

    public Long getId(){
        return id;
    }
    public void setId(Long courseId){
        this.id = courseId;
    }

    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }

    public String getDepartment(){
        return department;
    }
    public void setDepartment(String department){
        this.department = department;
    }

    public int getCapacity(){
        return capacity;
    }
    public void setCapacity(int capacity){
        this.capacity = capacity;
    }
    public Course(){}

}
