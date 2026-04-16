package com.example.studnetmanagement;

import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService service;

    @PostMapping
    public Student add(@RequestBody Student s){
         return service.addStudent(s);
    }

    @GetMapping
    public List<Student> getAll(){
        return service.getAllStudent();
    }

    @GetMapping("/{id}")
    public Student getById(@PathVariable int id){
        return service.getStudentById(id);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id){
        return service.deleteStudent(id);
    }

    @PutMapping("/{id}")
    public String updateStudent(@PathVariable int id, @RequestBody Student s){
        return service.updateStudent(id, s);

    }

}