package com.example.squad03.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Schema(description = "Dados para criação de um Contrato")
public class ContratoCreateDTO {

    @NotNull
    @Schema(description = "ID do órgão contratante vinculado ao contrato", example = "1")
    private Long orgaoContratanteId;

    @NotNull
    @Schema(description = "ID do funcionário responsável", example = "2")
    private Long responsavelId;

    @NotNull
    @Schema(description = "ID do representante da empresa contratada", example = "3")
    private Long representanteId;

    @NotNull
    @Schema(description = "Prazo de vigência do contrato", example = "2025-12-31")
    private LocalDate prazo;

    @NotNull
    @Schema(description = "Valor total do contrato", example = "10000.00")
    private BigDecimal valor;
}
