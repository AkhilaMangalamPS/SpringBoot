package com.example.studnetmanagement;

import org.springframework.stereotype.Service;

import java.util.List;
import  java.util.ArrayList;

@Service
public class StudentService {
    private List<Student> studentList = new ArrayList<>();

    public Student addStudent(Student student){
        for(Student s : studentList ){
            if(s.getId() == student.getId()){
                throw new RuntimeException("student Id already exists");
            }
        }
        studentList.add(student);
        return student;
    }

    public List<Student> getAllStudent(){
        return studentList;
    }

    public Student getStudentById(int id) {
        for (Student s : studentList) {
            if (s.getId() == id) {
                return s;
            }
        }
        throw new RuntimeException("Student with such ID doesnt exist!!");
    }

    public String deleteStudent(int id){
        for (Student s : studentList) {
            if (s.getId() == id) {
                Student item = getStudentById(id);
                studentList.remove(item);
                return "Deleted Successfully";
            }
        }
        return "No such student exist with this id";

    }

    public String updateStudent(int id, Student newStudent){
        Student s = getStudentById(id);
        s.setName(newStudent.getName());
        s.setAge(newStudent.getAge());
        return "Updated Successfully";
    }


}
