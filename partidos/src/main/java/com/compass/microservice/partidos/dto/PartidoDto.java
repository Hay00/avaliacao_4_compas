package com.compass.microservice.partidos.dto;

import java.time.format.DateTimeFormatter;

import com.compass.microservice.partidos.model.Partido;

import org.springframework.data.domain.Page;

import lombok.Getter;

public class PartidoDto {

    final DateTimeFormatter stdFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public PartidoDto(Partido partido) {
        this.id = partido.getId();
        this.nome = partido.getNome();
        this.sigla = partido.getSigla();
        this.ideologia = partido.getIdeologia();
        this.dataFundacao = partido.getDataFundacao().format(stdFormat);
    }

    @Getter
    private String id;

    @Getter
    private String nome;

    @Getter
    private String sigla;

    @Getter
    private String ideologia;

    @Getter
    private String dataFundacao;

    public static Page<PartidoDto> converter(Page<Partido> partidos) {
        return partidos.map(PartidoDto::new);
    }
}
