package org.example.courseenrollmentsystem.dto;

import java.time.LocalDate;

public record EnrollmentResponseDto (
        String studentName,
        String courseTitle,
        LocalDate enrollmentDate

)
{}
