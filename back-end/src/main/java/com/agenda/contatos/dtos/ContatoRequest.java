package com.agenda.contatos.dtos;

public record ContatoRequest(
    String name,
    String birthday,
    String nickname,
    String phonenumber,
    String email,
    String address,
    String occupation,
    Long grupoId
){}
