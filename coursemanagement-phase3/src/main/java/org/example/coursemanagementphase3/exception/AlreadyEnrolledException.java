package org.example.coursemanagementphase3.exception;

public class AlreadyEnrolledException extends RuntimeException{
    public AlreadyEnrolledException(Long studentId, Long courseId){
        super("Studnet with id "+ studentId+ " is already enrolled in course with id "+courseId);
    }
}
