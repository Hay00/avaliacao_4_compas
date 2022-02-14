package com.compass.microservice.associados.client;

import com.compass.microservice.associados.dto.PartidoDto;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("partidos")
public interface PartidoClient {

    @GetMapping("/partidos/{id}")
    public ResponseEntity<PartidoDto> get(@PathVariable String id);
}
