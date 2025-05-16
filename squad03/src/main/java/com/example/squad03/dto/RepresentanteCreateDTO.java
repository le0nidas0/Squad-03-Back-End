package com.example.squad03.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RepresentanteCreateDTO {
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private Long idOrgaoContratante;
}
