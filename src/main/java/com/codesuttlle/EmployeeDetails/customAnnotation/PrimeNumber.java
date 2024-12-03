package com.codesuttlle.EmployeeDetails.customAnnotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.METHOD})
@Constraint(validatedBy = PrimeNumberValidator.class)
public @interface PrimeNumber {
    String message() default "Email is already in use";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
