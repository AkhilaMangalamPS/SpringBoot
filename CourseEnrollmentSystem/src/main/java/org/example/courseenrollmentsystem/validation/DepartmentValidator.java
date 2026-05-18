package org.example.courseenrollmentsystem.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Set;

public class DepartmentValidator implements ConstraintValidator<ValidDepartment, String> {
    private static final Set<String> VALID_DEPARTMENTS = Set.of("CSE","ECE","MECH","CIVIL");
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context){
        if(value == null) return true;

        return VALID_DEPARTMENTS.contains(value.trim().toUpperCase());
    }

}
