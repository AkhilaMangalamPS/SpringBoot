package org.example.courseenrollmentsystem.controller;

import org.example.courseenrollmentsystem.dto.StudentDto;
import org.example.courseenrollmentsystem.entity.Student;
import org.example.courseenrollmentsystem.repository.StudentRepository;
import org.example.courseenrollmentsystem.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class studentController {
   @Autowired
   private StudentRepository studentRepository;

    @PostMapping
    public Student createStudent(@RequestBody StudentDto dto){
        Student student = new Student();
        student.setName(dto.name());
        student.setEmail(dto.email());

        return studentRepository.save(student);
    }


}
