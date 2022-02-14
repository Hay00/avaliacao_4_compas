package com.compass.microservice.partidos.config.validation.form;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IdeologiaValidation.class)
public @interface IdeologiaAnnotation {
    static List<String> allowedStates = List.of("Direita", "Centro", "Esquerda");

    String message() default "Ideologia inválida, valores válidos: Direita, Centro, Esquerda)";

    Class<?>[] groups() default {};

    public abstract Class<? extends Payload>[] payload() default {};

}
