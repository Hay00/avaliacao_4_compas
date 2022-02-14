package com.compass.microservice.partidos.repository;

import com.compass.microservice.partidos.model.Partido;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PartidoRepository extends MongoRepository<Partido, String> {

    Page<Partido> findByNomeContains(String nome, Pageable pageable);

    Page<Partido> findByIdeologiaContains(String ideologia, Pageable pageable);
}
