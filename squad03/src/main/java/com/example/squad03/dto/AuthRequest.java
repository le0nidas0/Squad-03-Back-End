package com.example.squad03.dto;

import io.swagger.v3.oas.annotations.media.Schema; // Import for @Schema
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Requisição de autenticação contendo email e senha do usuário.") // Schema for the class
public class AuthRequest {

    @Schema(description = "Endereço de email do usuário.", example = "usuario@example.com") // Schema for the email field
    private String email;

    @Schema(description = "Senha do usuário.", example = "minhasenha123") // Schema for the senha field
    private String senha;
}