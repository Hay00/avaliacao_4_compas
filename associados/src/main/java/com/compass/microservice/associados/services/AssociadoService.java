package com.compass.microservice.associados.services;

import com.compass.microservice.associados.client.PartidoClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssociadoService {

    @Autowired
    PartidoClient partidoClient;

    public boolean partidoExists(String id) {
        try {
            partidoClient.get(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
