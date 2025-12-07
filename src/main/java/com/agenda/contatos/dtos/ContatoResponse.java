package com.agenda.contatos.dtos;

public record ContatoResponse(
    Long id,
    String name,
    String nickname,
    String email,
    String address,
    String phonenumber,
    String occupation,
    String grupoName
){}
