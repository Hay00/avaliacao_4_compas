package com.compass.microservice.partidos.client;

import java.util.List;

import com.compass.microservice.partidos.dto.AssociadoDto;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "associados", path = "/associados")
public interface AssociadoClient {

    @GetMapping("/listFormat")
    public List<AssociadoDto> getAssociados(@RequestParam(name = "idPartido") String idPartido);
}
