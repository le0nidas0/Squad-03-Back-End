package com.example.squad03.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Schema(description = "Dados para criar um Agregado em um Contrato")
public class AgregadoCreateDTO {

    @NotNull
    @Schema(description = "Função desempenhada pelo colaborador neste contrato", example = "Coordenador de Projeto")
    private String funcao;

    @NotNull
    @Schema(description = "Data de início da atuação como agregado", example = "2025-06-01")
    private LocalDate dataInicio;

    @Schema(description = "Data de término (se aplicável)", example = "2025-12-31")
    private LocalDate dataFim;

    @NotNull
    @Schema(description = "ID do contrato ao qual o colaborador será agregado")
    private Long contratoId;

    @NotNull
    @Schema(description = "ID do colaborador que será agregado ao contrato")
    private Long colaboradorId;
}