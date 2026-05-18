package org.example.courseenrollmentsystem.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;


@Documented
@Constraint(validatedBy = DepartmentValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidDepartment {
    String message() default "Department name must be one of: CSE, ECE, MECH, CIVIL";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
