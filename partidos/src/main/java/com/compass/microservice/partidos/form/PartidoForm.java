package com.compass.microservice.partidos.form;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.compass.microservice.partidos.config.validation.form.IdeologiaAnnotation;
import com.compass.microservice.partidos.model.Partido;

import lombok.Setter;

@NotNull
public class PartidoForm {

    @NotNull
    @NotEmpty
    @Setter
    private String nome;

    @NotNull
    @NotEmpty
    @Setter
    private String sigla;

    @IdeologiaAnnotation
    @Setter
    private String ideologia;

    @NotNull
    @Setter
    private LocalDate dataFundacao;

    public Partido convert() {
        return new Partido(nome, sigla, ideologia, dataFundacao);
    }
}
