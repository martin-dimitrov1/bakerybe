package com.example.bakery.validation;

import com.example.bakery.validation.validators.AvailableUsernameValidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = AvailableUsernameValidation.class)
public @interface AvailableUsername {
    String message() default "Username is not available";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
