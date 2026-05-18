
package org.example.studentmanagement.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = StudentEmailValidator.class)
public @interface ValidStudentEmail{
    String message() default "Email must ends with @college.com";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

