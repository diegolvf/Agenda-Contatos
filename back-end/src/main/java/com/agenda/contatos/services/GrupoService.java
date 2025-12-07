package com.agenda.contatos.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.agenda.contatos.controllers.exceptions.DatabaseException;
import com.agenda.contatos.controllers.exceptions.ResourceNotFoundException;
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
                .orElseThrow(() -> new ResourceNotFoundException("Grupo ID " + id + " não encontrado."));
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
                .orElseThrow(() -> new ResourceNotFoundException("Grupo ID " + id + " não encontrado."));

        grupo.setName(request.name());

        grupoRepository.save(grupo);

        return toResponse(grupo);
    }

    // DELETAR
    public void delete(Long id) {
        try {
            grupoRepository.deleteById(id);
        } 
        catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Grupo ID " + id + " não encontrado.");
        } 
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Violação de integridade ao excluir o grupo.");
        }
    }

    // BUSCAR POR NOME
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
