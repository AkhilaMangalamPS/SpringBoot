package org.example.coursemanagementphase3.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.coursemanagementphase3.dto.EnrollmentDto;
import org.example.coursemanagementphase3.dto.StudentRequestDto;
import org.example.coursemanagementphase3.dto.StudentResponseDto;
import org.example.coursemanagementphase3.exception.StudentNotFoundException;
import org.example.coursemanagementphase3.service.StudentServiceImplementation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentController.class)
public class StudentControllerTest {
    @MockBean
    private StudentServiceImplementation studentService;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateStudent() throws Exception{
        StudentRequestDto request = new StudentRequestDto(
                "Akhila",
                "akhila@gmail.com"
        );
        StudentResponseDto response = new StudentResponseDto(
                1L,
                "Akhila",
                "akhila@gmail.com"
        );
        when(studentService.createStudent(any())).
                thenReturn(response);
        mockMvc.perform(post("/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Akhila"))
                .andExpect(jsonPath("$.email").value("akhila@gmail.com"));
    }

    @Test
    void testEnrollStudent() throws Exception{
        EnrollmentDto response = new EnrollmentDto(
                1L,
                LocalDate.now(),
                1L,
                1L
        );
        when(studentService.enrollStudent(1L,1L))
                .thenReturn(response);
        mockMvc.perform(post("/students/enroll")
                .param("studentId","1")
                .param("courseId","1"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.studentId").value(1L))
                .andExpect(jsonPath("$.courseId").value(1L));
    }

    @Test
    void testGetAllStudents() throws Exception{
        List<StudentResponseDto> response = List.of(
                new StudentResponseDto(1L,"John","abc@gmail.com"),
                new StudentResponseDto(2L,"Jerry","efg@gmail.com")
        );
        when(studentService.getAllStudents()).
                thenReturn(response);
        mockMvc.perform(get("/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].name").value("John"))
                .andExpect(jsonPath("$[1].name").value("Jerry"));
    }

    @Test
    void testCreateStudent_validation() throws Exception{
        String invalidJson = """
                {
                    "name" : "",
                    "email" : "invalid-email"
                }
        """;

        mockMvc.perform(post("/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(invalidJson))
                .andExpect(status().isBadRequest());

    }

    @Test
    void testEnrollStudent_StudentNotFound() throws Exception{
        when(studentService.enrollStudent(1L,1L))
                .thenThrow(new StudentNotFoundException(1L));

        mockMvc.perform(post("/students/enroll")
                .param("studentId","1")
                .param("courseId","1"))
                .andExpect(status().isNotFound());

    }



}
