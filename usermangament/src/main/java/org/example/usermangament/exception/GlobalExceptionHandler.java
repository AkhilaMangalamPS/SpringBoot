package org.example.usermangament.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DuplicateUser.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleDuplicate(DuplicateUser d){
        return d.getMessage();

    }
}
