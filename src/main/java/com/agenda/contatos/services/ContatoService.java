package com.agenda.contatos.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agenda.contatos.dtos.ContatoRequest;
import com.agenda.contatos.dtos.ContatoResponse;
import com.agenda.contatos.entities.Contato;
import com.agenda.contatos.entities.Grupo;
import com.agenda.contatos.repositories.ContatoRepository;
import com.agenda.contatos.repositories.GrupoRepository;

@Service
public class ContatoService {

    @Autowired
    private ContatoRepository contatoRepository;

    @Autowired
    private GrupoRepository grupoRepository;

    // LISTAR TODOS
    public List<ContatoResponse> findAll() {
        return contatoRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    // BUSCAR POR ID
    public ContatoResponse findById(Long id) {
        Contato contato = contatoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contato não encontrado."));
        return toResponse(contato);
    }

    // CRIAR CONTATO
    public ContatoResponse create(ContatoRequest request) {

        Grupo grupo = grupoRepository.findById(request.grupoId())
                .orElseThrow(() -> new RuntimeException("Grupo não encontrado."));

        Contato contato = new Contato();
        contato.setName(request.name());
        contato.setNickname(request.nickname());
        contato.setEmail(request.email());
        contato.setAddress(request.address());
        contato.setPhonenumber(request.phonenumber());
        contato.setOccupation(request.occupation());
        contato.setGrupo(grupo);

        contatoRepository.save(contato);

        return toResponse(contato);
    }

    // Atualizar Contato
    public ContatoResponse update(Long id, ContatoRequest request) {

        Contato contato = contatoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contato não encontrado."));

        Grupo grupo = grupoRepository.findById(request.grupoId())
                .orElseThrow(() -> new RuntimeException("Grupo não encontrado."));

        contato.setName(request.name());
        contato.setNickname(request.nickname());
        contato.setEmail(request.email());
        contato.setAddress(request.address());
        contato.setPhonenumber(request.phonenumber());
        contato.setOccupation(request.occupation());
        contato.setGrupo(grupo);

        contatoRepository.save(contato);

        return toResponse(contato);
    }

    // Remover contato
    public void delete(Long id) {
        contatoRepository.deleteById(id);
    }

    // Buscar por nome
    public List<ContatoResponse> searchByName(String name) {
        return contatoRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    // Buscar por apelido
    public List<ContatoResponse> searchByNickname(String nickname) {
        return contatoRepository.findByNicknameContainingIgnoreCase(nickname)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    // Buscar por email
    public List<ContatoResponse> searchByEmail(String email) {
        return contatoRepository.findByEmail(email)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    // BUSCAR por endereço
    public List<ContatoResponse> searchByAddress(String address) {
        return contatoRepository.findByAddressContainingIgnoreCase(address)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    // Conversão endidade para dto
    private ContatoResponse toResponse(Contato contato) {
        return new ContatoResponse(
            contato.getId(),
            contato.getName(),
            contato.getNickname(),
            contato.getEmail(),
            contato.getAddress(),
            contato.getPhonenumber(),
            contato.getOccupation(),
            contato.getGrupo().getName()
        );
    }
}
