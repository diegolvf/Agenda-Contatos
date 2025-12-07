package com.agenda.contatos.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agenda.contatos.controllers.exceptions.ResourceNotFoundException;
import com.agenda.contatos.dtos.ContatoRequest;
import com.agenda.contatos.dtos.ContatoResponse;
import com.agenda.contatos.entities.Contato;
import com.agenda.contatos.entities.Grupo;
import com.agenda.contatos.mappers.ContatoMapper;
import com.agenda.contatos.repositories.ContatoRepository;
import com.agenda.contatos.repositories.GrupoRepository;

@Service
public class ContatoService {
    @Autowired
    private ContatoRepository contatoRepository;

    @Autowired
    private GrupoRepository grupoRepository;

    // Listar Todos
    public List<ContatoResponse> findAll() {
        return contatoRepository.findAll()
                .stream()
                .map(ContatoMapper::toResponse) // Uso do Mapper
                .collect(Collectors.toList());
    }

    // Buscar por Id
    public ContatoResponse findById(Long id) {
        Contato contato = contatoRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Contato ID " + id + " não encontrado."));
        return ContatoMapper.toResponse(contato); // Uso do Mapper
    }

    // Criar Contato
    public ContatoResponse create(ContatoRequest request) {

        Grupo grupo = grupoRepository.findById(request.grupoId())
        .orElseThrow(() -> new ResourceNotFoundException("Grupo ID " + request.grupoId() + " não encontrado."));

        // 💡 Uso do Mapper: Converte o Request DTO para a Entidade Contato
        Contato contato = ContatoMapper.toEntity(request);

        contato.setGrupo(grupo);

        contatoRepository.save(contato);

        // 💡 Uso do Mapper: Converte a Entidade salva para o Response DTO
        return ContatoMapper.toResponse(contato);
    }

    // Atualizar Contato
    public ContatoResponse update(Long id, ContatoRequest request) {

        Contato contato = contatoRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Contato ID " + id + " não encontrado."));

        Grupo grupo = grupoRepository.findById(request.grupoId())
        .orElseThrow(() -> new ResourceNotFoundException("Grupo ID " + request.grupoId() + " não encontrado."));

        // Atualização manual dos campos no objeto existente (para manter a referência)
        contato.setName(request.name());
        contato.setNickname(request.nickname());
        contato.setEmail(request.email());
        contato.setAddress(request.address());
        contato.setPhonenumber(request.phonenumber());
        contato.setOccupation(request.occupation());
        contato.setGrupo(grupo);

        contatoRepository.save(contato);

        return ContatoMapper.toResponse(contato); // Uso do Mapper
    }

    // Deletar
    public void delete(Long id) {
        contatoRepository.deleteById(id);
    }

    // Buscar Nome
    public List<ContatoResponse> searchByName(String name) {
        return contatoRepository.findByNameContainingIgnoreCase(name)
        .stream()
        .map(ContatoMapper::toResponse) // Uso do Mapper
        .collect(Collectors.toList());
    }

    // Buscar Apelido
    public List<ContatoResponse> searchByNickname(String nickname) {
        return contatoRepository.findByNicknameContainingIgnoreCase(nickname)
                .stream()
                .map(ContatoMapper::toResponse) // Uso do Mapper
                .collect(Collectors.toList());
    }

    // Buscar Email
    public List<ContatoResponse> searchByEmail(String email) {
        return contatoRepository.findByEmail(email)
                .stream()
                .map(ContatoMapper::toResponse) // Uso do Mapper
                .collect(Collectors.toList());
    }

    // Buscar Endereço
    public List<ContatoResponse> searchByAddress(String address) {
        return contatoRepository.findByAddressContainingIgnoreCase(address)
                .stream()
                .map(ContatoMapper::toResponse) // Uso do Mapper
                .collect(Collectors.toList());
    }
}
