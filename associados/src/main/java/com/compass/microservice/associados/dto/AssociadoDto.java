package com.compass.microservice.associados.dto;

import java.time.format.DateTimeFormatter;

import com.compass.microservice.associados.model.Associado;

import org.springframework.data.domain.Page;

import lombok.Getter;

public class AssociadoDto {

    final DateTimeFormatter stdFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public AssociadoDto(Associado associado) {
        this.id = associado.getId();
        this.nome = associado.getNome();
        this.cargoPolitico = associado.getCargoPolitico();
        this.idPartido = associado.getIdPartido();
        this.dataNascimento = associado.getDataNascimento().format(stdFormat);
        this.sexo = associado.getSexo();
    }

    @Getter
    private String id;

    @Getter
    private String nome;

    @Getter
    private String idPartido;

    @Getter
    private String cargoPolitico;

    @Getter
    private String dataNascimento;

    @Getter
    private String sexo;

    public static Page<AssociadoDto> converter(Page<Associado> associados) {
        return associados.map(AssociadoDto::new);
    }
}
