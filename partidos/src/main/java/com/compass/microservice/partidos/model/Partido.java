package com.compass.microservice.partidos.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@NoArgsConstructor
public class Partido {

    public Partido(String nome, String sigla, String ideologia, LocalDate dataFundacao) {
        this.nome = nome;
        this.sigla = sigla;
        this.ideologia = ideologia;
        this.dataFundacao = dataFundacao;
    }

    @Id
    private String id;

    private String nome;

    private String sigla;

    private String ideologia;

    private LocalDate dataFundacao;
}
