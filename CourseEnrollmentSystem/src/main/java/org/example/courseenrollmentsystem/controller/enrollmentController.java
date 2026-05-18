package org.example.courseenrollmentsystem.controller;

import jakarta.validation.Valid;
import org.example.courseenrollmentsystem.dto.EnrollmentRequestDto;
import org.example.courseenrollmentsystem.dto.EnrollmentResponseDto;
import org.example.courseenrollmentsystem.service.EnrollmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enrollment")
public class enrollmentController {
    private final EnrollmentService enrollmentService;


    public enrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }


    @PostMapping
    public ResponseEntity<EnrollmentResponseDto> enroll(@Valid @RequestBody EnrollmentRequestDto dto){
        EnrollmentResponseDto response = enrollmentService.enroll(dto);
        return ResponseEntity.ok(response);

    }
}
