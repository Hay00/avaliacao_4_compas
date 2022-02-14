package com.compass.microservice.associados.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartidoDto {
    private String nome;
    private String sigla;
    private String ideologia;
    private String dataFundacao;
}
