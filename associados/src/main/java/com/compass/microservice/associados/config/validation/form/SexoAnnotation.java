package com.compass.microservice.associados.config.validation.form;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SexoValidation.class)
public @interface SexoAnnotation {
    static List<String> allowedStates = List.of("Masculino", "Feminino");

    String message() default "Sexo inválido, valores válidos: Masculino e Feminino)";

    Class<?>[] groups() default {};

    public abstract Class<? extends Payload>[] payload() default {};
}
