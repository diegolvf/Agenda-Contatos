package com.agenda.contatos.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agenda.contatos.entities.Contato;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long> {

    List<Contato> findByNameContainingIgnoreCase(String name);
    List<Contato> findByNicknameContainingIgnoreCase(String nickname);
    List<Contato> findByAddressContainingIgnoreCase(String address);
    List<Contato> findByEmail(String email);
    
}
