package com.compass.microservice.associados.config.validation.form;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SexoValidation implements ConstraintValidator<SexoAnnotation, String> {
    
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null) return false;
        return SexoAnnotation.allowedStates.contains(s);
    }

    @Override
    public void initialize(SexoAnnotation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

}
