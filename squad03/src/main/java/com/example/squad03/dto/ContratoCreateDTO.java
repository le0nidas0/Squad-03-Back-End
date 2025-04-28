package com.example.squad03.dto;

import com.example.squad03.enums.StatusContrato;
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
    @Schema(description = "Prazo do contrato", example = "2025-12-31")
    private LocalDate prazo;

    @NotNull
    @Schema(description = "Valor do contrato", example = "100000.00")
    private BigDecimal valor;

    @Schema(description = "Status do contrato", example = "ATIVO", allowableValues = {"ATIVO", "INATIVO", "ENCERRADO"})
    private StatusContrato status;

    @NotNull
    @Schema(description = "ID do órgão contratante")
    private Long orgaoContratanteId;

    @NotNull
    @Schema(description = "ID do funcionário responsável")
    private Long responsavelId;

    @NotNull
    @Schema(description = "ID do funcionário representante")
    private Long representanteId;
}
