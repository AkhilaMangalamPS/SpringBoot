package org.example.courseenrollmentsystem.service;

import org.example.courseenrollmentsystem.dto.EnrollmentRequestDto;
import org.example.courseenrollmentsystem.dto.EnrollmentResponseDto;

public interface EnrollmentService {

    EnrollmentResponseDto enroll(EnrollmentRequestDto dto);

}
