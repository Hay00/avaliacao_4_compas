package com.compass.microservice.associados.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Associado {

    public Associado(String nome, String cargoPolitico, LocalDate dataNascimento, String sexo) {
        this.nome = nome;
        this.idPartido = "";
        this.cargoPolitico = cargoPolitico;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
    }

    public Associado(String nome, String idPartido, String cargoPolitico, LocalDate dataNascimento, String sexo) {
        this.nome = nome;
        this.nome = idPartido;
        this.cargoPolitico = cargoPolitico;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
    }

    @Id
    private String id;

    private String idPartido;

    private String nome;

    private String cargoPolitico;

    private LocalDate dataNascimento;

    private String sexo;

}
