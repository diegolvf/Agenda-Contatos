package com.agenda.contatos.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.agenda.contatos.controllers.exceptions.DatabaseException;
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

    // LISTAR TODOS
    public List<ContatoResponse> findAll() {
        return contatoRepository.findAll()
                .stream()
                .map(ContatoMapper::toResponse)
                .collect(Collectors.toList());
    }

    // BUSCAR POR ID
    public ContatoResponse findById(Long id) {
        Contato contato = contatoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contato ID " + id + " não encontrado."));
        return ContatoMapper.toResponse(contato);
    }

    // CRIAR
    public ContatoResponse create(ContatoRequest request) {

        Grupo grupo = grupoRepository.findById(request.grupoId())
            .orElseThrow(() -> new ResourceNotFoundException("Grupo ID " + request.grupoId() + " não encontrado."));

        Contato contato = ContatoMapper.toEntity(request);
        contato.setGrupo(grupo);

        contatoRepository.save(contato);

        return ContatoMapper.toResponse(contato);
    }

    // ATUALIZAR
    public ContatoResponse update(Long id, ContatoRequest request) {

        Contato contato = contatoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Contato ID " + id + " não encontrado."));

        Grupo grupo = grupoRepository.findById(request.grupoId())
            .orElseThrow(() -> new ResourceNotFoundException("Grupo ID " + request.grupoId() + " não encontrado."));

        contato.setName(request.name());
        contato.setNickname(request.nickname());
        contato.setEmail(request.email());
        contato.setAddress(request.address());
        contato.setPhonenumber(request.phonenumber());
        contato.setOccupation(request.occupation());
        contato.setBirthday(request.birthday() == null ? null : java.time.LocalDate.parse(request.birthday()));
        contato.setGrupo(grupo);

        contatoRepository.save(contato);

        return ContatoMapper.toResponse(contato);
    }

    // DELETAR
    public void delete(Long id) {
        try {
            contatoRepository.deleteById(id);
        } 
        catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Contato ID " + id + " não encontrado.");
        } 
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Violação de integridade ao excluir o contato.");
        }
    }

    // BUSCAR POR NOME
    public List<ContatoResponse> searchByName(String name) {
        return contatoRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(ContatoMapper::toResponse)
                .collect(Collectors.toList());
    }

    // BUSCAR POR APELIDO
    public List<ContatoResponse> searchByNickname(String nickname) {
        return contatoRepository.findByNicknameContainingIgnoreCase(nickname)
            .stream()
            .map(ContatoMapper::toResponse)
            .collect(Collectors.toList());
    }

    // BUSCAR POR EMAIL
    public List<ContatoResponse> searchByEmail(String email) {
        return contatoRepository.findByEmail(email)
            .stream()
            .map(ContatoMapper::toResponse)
            .collect(Collectors.toList());
    }

    // BUSCAR POR ENDEREÇO
    public List<ContatoResponse> searchByAddress(String address) {
        return contatoRepository.findByAddressContainingIgnoreCase(address)
            .stream()
            .map(ContatoMapper::toResponse)
            .collect(Collectors.toList());
    }
}
