package com.agenda.contatos.mappers;

import com.agenda.contatos.dtos.ContatoRequest;
import com.agenda.contatos.dtos.ContatoResponse;
import com.agenda.contatos.entities.Contato;

public class ContatoMapper {

    // Converte DTO Request para Entidade
    public static Contato toEntity(ContatoRequest request) {
        Contato contato = new Contato();
        contato.setName(request.name());
        contato.setNickname(request.nickname());
        contato.setEmail(request.email());
        contato.setAddress(request.address());
        contato.setPhonenumber(request.phonenumber());
        contato.setOccupation(request.occupation());
        // O campo 'grupo' é setado separadamente no Service.
        return contato;
    }

    // Converte Entidade para DTO Response
    public static ContatoResponse toResponse(Contato contato) {
        // Garantindo que não haja NullPointerException caso o grupo seja nulo (embora não deva ser)
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
            grupoName
        );
    }   
}
