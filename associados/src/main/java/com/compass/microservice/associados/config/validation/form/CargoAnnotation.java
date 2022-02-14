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
@Constraint(validatedBy = CargoValidation.class)
public @interface CargoAnnotation {
    static List<String> allowedStates = List.of(
            "Vereador", "Prefeito",
            "Deputado Estadual",
            "Deputado Federal",
            "Senador", "Governador",
            "Presidente", "Nenhum");

    String message() default "Cargo inválido, valores válidos: "
            + "Vereador, Prefeito, Deputado Estadual, Deputado Federal, "
            + "Senador, Governador, Presidente e Nenhum";

    Class<?>[] groups() default {};

    public abstract Class<? extends Payload>[] payload() default {};
}
