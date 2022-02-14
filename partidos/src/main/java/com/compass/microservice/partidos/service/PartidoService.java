package com.compass.microservice.partidos.service;

import java.util.List;

import com.compass.microservice.partidos.client.AssociadoClient;
import com.compass.microservice.partidos.dto.AssociadoDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartidoService {

    @Autowired
    AssociadoClient associadoClient;

    public List<AssociadoDto> getAssociados(String idPartido) {
        return associadoClient.getAssociados(idPartido);
    }
}
