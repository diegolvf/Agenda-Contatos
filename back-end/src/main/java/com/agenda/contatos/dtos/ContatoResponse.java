package com.agenda.contatos.dtos;

import java.time.LocalDate;

public record ContatoResponse(
    Long id,
    String name,
    LocalDate birthday,
    String nickname,
    String email,
    String address,
    String phonenumber,
    String occupation,
    String grupoName
){}
