package com.agenda.contatos.mappers;

import com.agenda.contatos.dtos.ContatoRequest;
import com.agenda.contatos.dtos.ContatoResponse;
import com.agenda.contatos.entities.Contato;
import java.time.LocalDate;

public class ContatoMapper {

    public static Contato toEntity(ContatoRequest request) {
        Contato contato = new Contato();
        contato.setName(request.name());

        if (request.birthday() != null && !request.birthday().isEmpty()) {
            try {
                contato.setBirthday(LocalDate.parse(request.birthday()));
            } catch (Exception e) {
                System.err.println("Erro ao converter data de aniversário: " + request.birthday());
            }
        }

        contato.setNickname(request.nickname());
        contato.setPhonenumber(request.phonenumber());
        contato.setEmail(request.email());
        contato.setAddress(request.address());
        contato.setOccupation(request.occupation());

        return contato;
    }

    public static ContatoResponse toResponse(Contato contato) {
        String grupoName = (contato.getGrupo() != null) ? contato.getGrupo().getName() : null;

        return new ContatoResponse(
                contato.getId(),
                contato.getName(),
                contato.getBirthday(),
                contato.getNickname(),
                contato.getEmail(),
                contato.getAddress(),
                contato.getPhonenumber(),
                contato.getOccupation(),
                grupoName);
    }
}