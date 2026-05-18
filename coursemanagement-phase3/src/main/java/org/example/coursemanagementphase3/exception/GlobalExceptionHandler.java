package org.example.coursemanagementphase3.exception;

import org.example.coursemanagementphase3.entity.Course;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    //Student not found
    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<String> handleStudentNotFound(StudentNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
    }

    //Course not found
    @ExceptionHandler(CourseNotFoundException.class)
    public ResponseEntity<String> handleCourseNotFound(CourseNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    //Already enrolled exception
    @ExceptionHandler(AlreadyEnrolledException.class)
    public ResponseEntity<String> handleAlreadyEnrolledException(AlreadyEnrolledException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    //Generic fallback
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneral(Exception ex){
        return new ResponseEntity<>("Something went wrong"+ ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
