package com.compass.microservice.associados.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.compass.microservice.associados.dto.AssociadoDto;
import com.compass.microservice.associados.form.AssociadoForm;
import com.compass.microservice.associados.form.PartidoAssociadoForm;
import com.compass.microservice.associados.model.Associado;
import com.compass.microservice.associados.repository.AssociadoRepository;
import com.compass.microservice.associados.services.AssociadoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
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
@RequestMapping("/associados")
public class AssociadoController {

    @Autowired
    AssociadoRepository associadoRepository;

    @Autowired
    AssociadoService associadoService;

    @GetMapping
    public Page<AssociadoDto> list(@RequestParam(required = false) String cargoPolitico,
            @RequestParam(required = false) String idPartido,
            @PageableDefault(sort = "id", direction = Direction.DESC) Pageable pageable) {

        if (cargoPolitico != null) {
            return AssociadoDto.converter(associadoRepository.findByCargoPoliticoContains(cargoPolitico, pageable));
        }
        if (idPartido != null) {
            return AssociadoDto.converter(associadoRepository.findByIdPartido(cargoPolitico, pageable));
        }
        return AssociadoDto.converter(associadoRepository.findAll(pageable));
    }

    @GetMapping("/listFormat")
    public List<AssociadoDto> listAll(@RequestParam String idPartido) {
        List<AssociadoDto> returnList = new ArrayList<>();
        associadoRepository.findByIdPartido(idPartido)
                .forEach(associado -> returnList.add(new AssociadoDto(associado)));
        return returnList;
    }

    @PostMapping
    @CacheEvict(value = "cachedAssocaidos", allEntries = true)
    public ResponseEntity<AssociadoDto> create(@RequestBody @Valid AssociadoForm form,
            UriComponentsBuilder uriBuilder) {

        Associado associado = form.convert();
        associadoRepository.save(associado);
        URI uri = uriBuilder.path("/associados").buildAndExpand(associado.getId()).toUri();

        return ResponseEntity.created(uri).body(new AssociadoDto(associado));
    }

    @PostMapping("/partidos")
    @CacheEvict(value = "cachedAssocaidos", allEntries = true)
    public ResponseEntity<AssociadoDto> addPartido(@RequestBody @Valid PartidoAssociadoForm form) {
        if (!associadoService.partidoExists(form.getIdPartido())) {
            return ResponseEntity.notFound().build();
        }
        return associadoRepository.findById(form.getIdAssociado())
                .map(associado -> {
                    associado.setIdPartido(form.getIdPartido());
                    associadoRepository.save(associado);
                    return ResponseEntity.ok(new AssociadoDto(associado));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssociadoDto> get(@PathVariable String id) {
        return associadoRepository.findById(id)
                .map(associado -> ResponseEntity.ok(new AssociadoDto(associado)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @CacheEvict(value = "cachedAssocaidos", allEntries = true)
    public ResponseEntity<AssociadoDto> update(@PathVariable String id, @Valid @RequestBody AssociadoForm form) {
        Associado formData = form.convert();
        return associadoRepository.findById(id)
                .map(associado -> {
                    associado.setNome(formData.getNome());
                    associado.setCargoPolitico(formData.getCargoPolitico());
                    associado.setDataNascimento(formData.getDataNascimento());
                    associado.setSexo(formData.getSexo());
                    associadoRepository.save(associado);
                    return ResponseEntity.ok(new AssociadoDto(associado));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = "cachedAssocaidos", allEntries = true)
    public ResponseEntity<Object> remove(@PathVariable String id) {
        return associadoRepository.findById(id)
                .map(associado -> {
                    associadoRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{idAssociado}/partidos/{idPartido}")
    @CacheEvict(value = "cachedAssocaidos", allEntries = true)
    public ResponseEntity<Object> removePartido(@PathVariable String idAssociado, @PathVariable String idPartido) {
        if (!associadoService.partidoExists(idPartido)) {
            return ResponseEntity.notFound().build();
        }

        return associadoRepository.findById(idAssociado)
                .map(associado -> {
                    associado.setIdPartido("");
                    associadoRepository.save(associado);
                    return ResponseEntity.ok().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
