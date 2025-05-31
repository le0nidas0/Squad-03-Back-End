package com.example.squad03.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RepresentanteCreateDTO {

    private Long id;

    @NotNull
    private String nome;

    @NotNull
    private String cpf;

    @NotNull
    private String email;

    @NotNull
    private String telefone;

    private Long idOrgaoContratante;
}
