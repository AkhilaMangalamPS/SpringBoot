package org.example.courseenrollmentsystem.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.example.courseenrollmentsystem.validation.ValidDepartment;


public record CourseDto (
     @NotBlank(message = "Course name is required")
     String title,

     @NotBlank(message = "Department is required")
     @ValidDepartment
     String department,

     @Min(value = 1, message = "Capacity must be atleast 1")
     @Max(value = 100, message = "Capacity cannot exceed 100")
     int capacity

)
{}
