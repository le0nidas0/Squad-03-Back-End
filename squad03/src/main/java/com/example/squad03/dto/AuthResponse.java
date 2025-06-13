package com.example.squad03.dto;

import io.swagger.v3.oas.annotations.media.Schema; // Import for @Schema
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Resposta de autenticação contendo o token JWT.") // Schema for the class
public class AuthResponse {

    @Schema(description = "Token de autenticação JWT.", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...") // Schema for the token field
    private String token;

    @Schema(description = "Roles/autoridades do usuário.", example = "[\"ROLE_USER\",\"ROLE_ADMIN\"]")
    private Set<String> roles;
}