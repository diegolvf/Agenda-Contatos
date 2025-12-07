package com.agenda.contatos.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agenda.contatos.dtos.GrupoRequest;
import com.agenda.contatos.dtos.GrupoResponse;
import com.agenda.contatos.entities.Grupo;
import com.agenda.contatos.repositories.GrupoRepository;

@Service
public class GrupoService {

 @Autowired
    private GrupoRepository grupoRepository;

    // LISTAR TODOS
    public List<GrupoResponse> findAll() {
        return grupoRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    // BUSCAR POR ID
    public GrupoResponse findById(Long id) {
        Grupo grupo = grupoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Grupo não encontrado."));
        return toResponse(grupo);
    }

    // CRIAR
    public GrupoResponse create(GrupoRequest request) {

        Grupo grupo = new Grupo();
        grupo.setName(request.name());

        grupoRepository.save(grupo);

        return toResponse(grupo);
    }

    // ATUALIZAR
    public GrupoResponse update(Long id, GrupoRequest request) {

        Grupo grupo = grupoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Grupo não encontrado."));

        grupo.setName(request.name());

        grupoRepository.save(grupo);

        return toResponse(grupo);
    }

    // REMOVER
    public void delete(Long id) {
        grupoRepository.deleteById(id);
    }

    // BUSCA POR NOME
    public List<GrupoResponse> searchByName(String name) {
        return grupoRepository.findByNameContainingIgnoreCase(name)
        .stream()
        .map(this::toResponse)
        .collect(Collectors.toList());
    }

    // CONVERSÃO ENTIDADE → DTO
    private GrupoResponse toResponse(Grupo grupo) {
        return new GrupoResponse(
            grupo.getId(),
            grupo.getName()
        );
    }
    
}
