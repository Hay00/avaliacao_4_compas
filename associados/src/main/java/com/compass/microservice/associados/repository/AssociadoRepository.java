package com.compass.microservice.associados.repository;

import java.util.List;

import com.compass.microservice.associados.model.Associado;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AssociadoRepository extends MongoRepository<Associado, String> {

    Page<Associado> findByNomeContains(String nome, Pageable pageable);

    Page<Associado> findByCargoPoliticoContains(String cargoPolitico, Pageable pageable);

    List<Associado> findByIdPartido(String idPartido);

    Page<Associado> findByIdPartido(String idPartido, Pageable pageable);
}
