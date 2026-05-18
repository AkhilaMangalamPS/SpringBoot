package org.example.studentmanagement.service;

import org.example.studentmanagement.dto.studentDto;
import org.example.studentmanagement.entity.Student;
import org.example.studentmanagement.exception.ResourceNotFoundException;
import org.example.studentmanagement.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository repo;

    public StudentService(StudentRepository repo) {
        this.repo = repo;
    }
    public studentDto create(studentDto dto){
        Student s = new Student();
        s.setName(dto.name());
        s.setAge(dto.age());
        s.setEmail(dto.email());
        Student saved = repo.save(s);
        return new studentDto(saved.getName() , s.getEmail(), s.getAge());

    }

    public List<studentDto> getAll(){
        return repo.findAll().stream().map(s -> new studentDto(s.getName(), s.getEmail(), s.getAge())).toList();

    }

    public studentDto getById(long id){
        Student s = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        return new studentDto(s.getName(), s.getEmail(), s.getAge());
    }

    public studentDto update(long id, studentDto dto){
        Student s =repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        s.setName(dto.name());
        s.setEmail(dto.email());
        s.setAge(dto.age());

        Student updated = repo.save(s);
        return new studentDto(updated.getName(), updated.getEmail(), updated.getAge());
    }

    public void delete(long id){
        Student s = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        repo.delete(s);
    }
}
