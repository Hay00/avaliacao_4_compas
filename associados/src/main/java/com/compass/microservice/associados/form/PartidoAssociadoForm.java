package com.compass.microservice.associados.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class PartidoAssociadoForm {

    @NotNull
    @NotEmpty
    private String idPartido;

    @NotNull
    @NotEmpty
    private String idAssociado;
}
