package com.example.squad03.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Schema(description = "Dados para criar uma Repactuação de contrato")
public class RepactuacaoCreateDTO {

    @NotNull
    @Schema(description = "Índice utilizado para repactuar (ex.: IPCA-15, IGP-M)", example = "IPCA-15")
    private String indice;

    @NotNull
    @Schema(description = "Data-base da repactuação", example = "2025-01-01")
    private LocalDate dataBase;

    @NotNull
    @Schema(description = "Valor anterior ao reajuste", example = "100000.00")
    private BigDecimal valorAnterior;

    @NotNull
    @Schema(description = "Valor repactuado após aplicar índice", example = "105000.00")
    private BigDecimal valorRepactuado;

    @NotNull
    @Schema(description = "Justificativa para este reajuste", example = "Índice IPCA acumulado no período")
    private String justificativa;

    @NotNull
    @Schema(description = "ID do contrato ao qual será aplicada a repactuação")
    private Long contratoId;
}
