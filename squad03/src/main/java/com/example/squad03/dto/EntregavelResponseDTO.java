package com.example.squad03.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Schema(description = "Dados de retorno de um Entregável")
public class EntregavelResponseDTO {

    @Schema(description = "ID do entregável", example = "1")
    private Long idEntregavel;

    @Schema(description = "Título do entregável", example = "Desenvolvimento do módulo de autenticação")
    private String titulo;

    @Schema(description = "Descrição do entregável", example = "Desenvolvimento do módulo de autenticação")
    private String descricao;

    @Schema(description = "Prazo de entrega do entregável", example = "2025-12-31")
    private LocalDate prazoEntrega;

    @Schema(description = "Status do entregável", example = "PENDENTE", allowableValues = {"PENDENTE", "EM_ANDAMENTO", "CONCLUIDO"})
    private String status;

    @Schema(description = "ID do colaborador responsável pelo entregável", example = "1")
    private Long responsavelId;

    @Schema(description = "ID do contrato associado ao entregável", example = "1")
    private Long contratoId;
}
