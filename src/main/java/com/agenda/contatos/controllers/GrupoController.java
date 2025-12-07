package com.agenda.contatos.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.agenda.contatos.dtos.GrupoRequest;
import com.agenda.contatos.dtos.GrupoResponse;
import com.agenda.contatos.services.GrupoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/grupos")
public class GrupoController {

    @Autowired
    private GrupoService service;
    
    @GetMapping
    public ResponseEntity<List<GrupoResponse>> getAll() {
        List<GrupoResponse> grupos = service.findAll();
        return ResponseEntity.ok(grupos);
    }

    // BUSCAR POR ID
    @GetMapping("/{id}")
    public ResponseEntity<GrupoResponse> getById(@PathVariable Long id) {
        GrupoResponse grupo = service.findById(id);
        return ResponseEntity.ok(grupo);
    }

    // CRIAR
    @PostMapping
    public ResponseEntity<GrupoResponse> create(@Valid @RequestBody GrupoRequest request) {
        GrupoResponse grupo = service.create(request);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(grupo.id())
                .toUri();

        return ResponseEntity.created(location).body(grupo);
    }

    // ATUALIZAR
    @PutMapping("/{id}")
    public ResponseEntity<GrupoResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody GrupoRequest request) {

        GrupoResponse grupo = service.update(id, request);
        return ResponseEntity.ok(grupo);
    }

    // DELETAR
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    
}
