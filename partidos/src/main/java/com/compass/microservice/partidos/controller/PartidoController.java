package com.compass.microservice.partidos.controller;

import java.net.URI;

import javax.validation.Valid;

import com.compass.microservice.partidos.dto.AssociadoDto;
import com.compass.microservice.partidos.dto.PartidoDto;
import com.compass.microservice.partidos.form.PartidoForm;
import com.compass.microservice.partidos.model.Partido;
import com.compass.microservice.partidos.repository.PartidoRepository;
import com.compass.microservice.partidos.service.PartidoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/partidos")
public class PartidoController {

    @Autowired
    PartidoRepository partidoRepository;

    @Autowired
    PartidoService partidoService;

    @GetMapping
    public Page<PartidoDto> list(@RequestParam(required = false) String ideologia,
            @PageableDefault(sort = "id", direction = Direction.DESC) Pageable pageable) {
        if (ideologia == null) {
            return PartidoDto.converter(partidoRepository.findAll(pageable));
        }
        return PartidoDto.converter(partidoRepository.findByIdeologiaContains(ideologia, pageable));
    }

    @PostMapping
    @CacheEvict(value = "cachedPartidos", allEntries = true)
    public ResponseEntity<PartidoDto> create(@RequestBody @Valid PartidoForm form,
            UriComponentsBuilder uriBuilder) {
        Partido partido = form.convert();
        partidoRepository.save(partido);
        URI uri = uriBuilder.path("/partidos").buildAndExpand(partido.getId()).toUri();
        return ResponseEntity.created(uri).body(new PartidoDto(partido));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PartidoDto> get(@PathVariable String id) {
        return partidoRepository.findById(id)
                .map(partido -> ResponseEntity.ok(new PartidoDto(partido)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/associados")
    public Page<AssociadoDto> getAssociados(@PathVariable String id) {
        return new PageImpl<>(partidoService.getAssociados(id));
    }

    @PutMapping("/{id}")
    @CacheEvict(value = "cachedPartidos", allEntries = true)
    public ResponseEntity<PartidoDto> update(@PathVariable String id, @RequestBody PartidoForm form) {
        Partido partidoForm = form.convert();
        return partidoRepository.findById(id)
                .map(partido -> {
                    partido.setNome(partidoForm.getNome());
                    partido.setSigla(partidoForm.getSigla());
                    partido.setIdeologia(partidoForm.getIdeologia());
                    partido.setDataFundacao(partidoForm.getDataFundacao());
                    partidoRepository.save(partido);
                    return ResponseEntity.ok(new PartidoDto(partido));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = "cachedPartidos", allEntries = true)
    public ResponseEntity<Object> remove(@PathVariable String id) {
        return partidoRepository.findById(id)
                .map(partido -> {
                    partidoRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
