package com.compass.microservice.associados.config.validation.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class FormErrorDto {

    @Getter
    private String campo;

    @Getter
    private String erro;
}