package com.agenda.contatos.dtos;

public record ContatoRequest(
    String name,
    String nickname,
    String email,
    String address,
    String phonenumber,
    String occupation,
    Long grupoId
){}
