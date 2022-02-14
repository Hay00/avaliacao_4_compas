package com.compass.microservice.partidos.config.validation.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class FormErrorDto {

    @Getter
    private String campo;

    @Getter
    private String erro;
}