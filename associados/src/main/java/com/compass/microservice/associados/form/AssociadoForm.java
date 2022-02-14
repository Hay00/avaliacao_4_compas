package com.compass.microservice.associados.form;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.compass.microservice.associados.config.validation.form.CargoAnnotation;
import com.compass.microservice.associados.config.validation.form.SexoAnnotation;
import com.compass.microservice.associados.model.Associado;

import lombok.Setter;

@NotNull
public class AssociadoForm {

    @NotNull
    @NotEmpty
    @Setter
    private String nome;

    @Setter
    private String idPartido;

    @CargoAnnotation
    @Setter
    private String cargoPolitico;

    @NotNull
    @Setter
    private LocalDate dataNascimento;

    @SexoAnnotation
    @Setter
    private String sexo;

    public Associado convert() {
        if (idPartido == null) {
            return new Associado(nome, cargoPolitico, dataNascimento, sexo);
        }
        return new Associado(nome, idPartido, cargoPolitico, dataNascimento, sexo);
    }
}
