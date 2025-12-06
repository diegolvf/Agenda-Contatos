package com.agenda.contatos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agenda.contatos.entities.Grupo;
import com.agenda.contatos.repositories.GrupoRepository;

@Service
public class GrupoService {

        @Autowired
    private GrupoRepository grupoRepo;

    // Criar grupo
    public Grupo create(Grupo grupo) {
        return grupoRepo.save(grupo);
    }

    // Listar
    public List<Grupo> findAll() {
        return grupoRepo.findAll();
    }

    // Buscar por ID
    public Grupo findById(Long id) {
        return grupoRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Grupo não encontrado."));
    }

    // Atualizar
    public Grupo update(Long id, Grupo novo) {
        Grupo grupo = findById(id);

        grupo.setName(novo.getName());

        return grupoRepo.save(grupo);
    }

    // Deletar
    public void delete(Long id) {
        grupoRepo.deleteById(id);
    }

    // Buscar por nome
    public List<Grupo> findByName(String name) {
        return grupoRepo.findByName(name);
    }
    
}
