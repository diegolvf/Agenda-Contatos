package com.agenda.contatos.mappers;

import com.agenda.contatos.dtos.GrupoRequest;
import com.agenda.contatos.dtos.GrupoResponse;
import com.agenda.contatos.entities.Grupo;

public class GrupoMapper {

    public static Grupo toEntity(GrupoRequest request) {
        Grupo grupo = new Grupo();
        
        grupo.setName(request.name());
        
        return grupo;
    }

    public static GrupoResponse toResponse(Grupo grupo) {
        return new GrupoResponse(
            grupo.getId(),
            grupo.getName()
        );
    }
}
