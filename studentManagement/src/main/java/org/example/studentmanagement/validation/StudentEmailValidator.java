package org.example.studentmanagement.validation;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;

import java.lang.annotation.*;

public class StudentEmailValidator implements ConstraintValidator<ValidStudentEmail, String> {
    @Override
    public boolean isValid(String email, ConstraintValidatorContext context){
        return email != null && email.endsWith("@college.com");
    }
}