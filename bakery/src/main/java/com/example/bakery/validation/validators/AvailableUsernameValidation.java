package com.example.bakery.validation.validators;

import com.example.bakery.models.RegistrationUser;
import com.example.bakery.services.AuthenticationService;
import com.example.bakery.validation.AvailableUsername;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@RequiredArgsConstructor
public class AvailableUsernameValidation implements ConstraintValidator<AvailableUsername, String> {
    private final AuthenticationService authenticationService;

    @Override
    public void initialize(AvailableUsername constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return authenticationService.isUsernameAvailable(value);
    }
}
