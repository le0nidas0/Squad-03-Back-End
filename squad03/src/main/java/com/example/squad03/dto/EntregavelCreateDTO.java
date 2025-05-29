package com.example.squad03.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Dados para criação de um Entregável")
public class EntregavelCreateDTO {

    @Schema(description = "título do entregável", example = "Desenvolvimento do módulo de autenticação")
    private String titulo;

    @NotNull
    @Schema(description = "Descrição do entregável", example = "Desenvolvimento do módulo de autenticação")
    private String descricao;

    @NotNull
    @Schema(description = "Prazo de entrega do entregável", example = "2025-12-31")
    private String prazoEntrega;

    @NotNull
    @Schema(description = "Status do entregável", example = "PENDENTE", allowableValues = {"PENDENTE", "EM_ANDAMENTO", "CONCLUIDO"})
    private String status;

    @NotNull
    @Schema(description = "ID do colaborador responsável pelo entregável")
    private Long responsavelId;

    @NotNull
    @Schema(description = "ID do contrato associado ao entregável")
    private Long contratoId;
}
