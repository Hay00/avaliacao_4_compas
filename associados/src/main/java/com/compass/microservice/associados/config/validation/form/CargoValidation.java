package com.compass.microservice.associados.config.validation.form;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CargoValidation implements ConstraintValidator<CargoAnnotation, String> {
    
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null) return false;
        return CargoAnnotation.allowedStates.contains(s);
    }

    @Override
    public void initialize(CargoAnnotation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

}
