package com.compass.microservice.partidos.config.validation.form;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IdeologiaValidation implements ConstraintValidator<IdeologiaAnnotation, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null) return false;
        return IdeologiaAnnotation.allowedStates.contains(s);
    }

    @Override
    public void initialize(IdeologiaAnnotation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
