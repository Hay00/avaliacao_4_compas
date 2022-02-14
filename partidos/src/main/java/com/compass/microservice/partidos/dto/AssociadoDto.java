package com.compass.microservice.partidos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssociadoDto {
    private String id;

    private String nome;

    private String idPartido;

    private String cargoPolitico;

    private String dataNascimento;

    private String sexo;
}
