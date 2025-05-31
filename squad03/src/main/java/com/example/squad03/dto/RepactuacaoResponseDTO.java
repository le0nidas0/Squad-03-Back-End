package com.example.squad03.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Schema(description = "Dados de retorno de uma Repactuação")
public class RepactuacaoResponseDTO {

    @Schema(description = "ID da repactuação", example = "1")
    private Long idRepactuacao;

    @Schema(description = "Índice utilizado para o reajuste", example = "IPCA-15")
    private String indice;

    @Schema(description = "Data-base do reajuste", example = "2025-01-01")
    private LocalDate dataBase;

    @Schema(description = "Valor anterior ao reajuste", example = "100000.00")
    private BigDecimal valorAnterior;

    @Schema(description = "Valor repactuado após reajuste", example = "105000.00")
    private BigDecimal valorRepactuado;

    @Schema(description = "Justificativa para a repactuação")
    private String justificativa;

    @Schema(description = "Data de criação do registro de repactuação", example = "2025-06-01T10:00:00")
    private LocalDateTime criadoEm;

    @Schema(description = "Contrato associado (dados básicos)")
    private ContratoSimplesDTO contrato;
}
