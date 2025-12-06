package com.agenda.contatos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    // Listar todos
    public List<Contato> findAll() {
        return contatoRepository.findAll();
    }

    // Buscar por ID
    public Contato findById(Long id) {
        return contatoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contato não encontrado."));
    }

    // Criar contato
    public Contato create(Contato contato, Long grupoId) {

        Grupo grupo = grupoRepository.findById(grupoId)
                .orElseThrow(() -> new RuntimeException("Grupo não encontrado."));

        contato.setGrupo(grupo);

        return contatoRepository.save(contato);
    }

    // Atualizar
    public Contato update(Long id, Contato novo, Long grupoId) {

        Contato contato = findById(id);
        Grupo grupo = grupoRepository.findById(grupoId)
                .orElseThrow(() -> new RuntimeException("Grupo não encontrado."));

        contato.setName(novo.getName());
        contato.setNickname(novo.getNickname());
        contato.setEmail(novo.getEmail());
        contato.setAddress(novo.getAddress());
        contato.setBirthday(novo.getBirthday());
        contato.setPhonenumber(novo.getPhonenumber());
        contato.setOccupation(novo.getOccupation());
        contato.setGrupo(grupo);

        return contatoRepository.save(contato);
    }

    // Remover
    public void delete(Long id) {
        contatoRepository.deleteById(id);
    }

    // Pesquisar por nome
    public List<Contato> searchByName(String name) {
        return contatoRepository.findByNameContainingIgnoreCase(name);
    }

    // Pesquisar por apelido
    public List<Contato> searchByNickname(String nickname) {
        return contatoRepository.findByNicknameContainingIgnoreCase(nickname);
    }

    // Pesquisar por email
    public List<Contato> searchByEmail(String email) {
        return contatoRepository.findByEmail(email);
    }

    // Pesquisar por endereço
    public List<Contato> searchByAddress(String address) {
        return contatoRepository.findByAddressContainingIgnoreCase(address);
    }
    
}
