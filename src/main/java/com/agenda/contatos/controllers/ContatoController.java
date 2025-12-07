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

import com.agenda.contatos.dtos.ContatoRequest;
import com.agenda.contatos.dtos.ContatoResponse;
import com.agenda.contatos.services.ContatoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/contatos")
public class ContatoController {

  @Autowired
    private ContatoService service;

    @GetMapping
    public ResponseEntity<List<ContatoResponse>> getAll() {
        List<ContatoResponse> contatos = service.findAll();
        return ResponseEntity.ok(contatos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContatoResponse> getById(@PathVariable Long id) {
        ContatoResponse contato = service.findById(id);
        return ResponseEntity.ok(contato);
    }

    @PostMapping
    public ResponseEntity<ContatoResponse> create(@Valid @RequestBody ContatoRequest request) {
        ContatoResponse contato = service.create(request);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(contato.id())
                .toUri();

        return ResponseEntity.created(location).body(contato);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContatoResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody ContatoRequest request) {

        ContatoResponse contato = service.update(id, request);
        return ResponseEntity.ok(contato);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    
}
