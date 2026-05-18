package org.example.studentmanagement.controller;

import jakarta.validation.Valid;
import org.example.studentmanagement.dto.studentDto;
import org.example.studentmanagement.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/studets")
public class StudentController {
    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @PostMapping
    public studentDto create(@Valid @RequestBody studentDto dto){
        return service.create(dto);
    }
    @GetMapping
    public List<studentDto> getAll(){
        return service.getAll();
    }
    @GetMapping("/{id}")
    public studentDto getById(@PathVariable Long id){
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public studentDto update(@PathVariable  Long id,@RequestBody studentDto dto){
        return service.update(id,dto);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable long id){
        service.delete(id);
        return "Deleted successfully";
    }

}
