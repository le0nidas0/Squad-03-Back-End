package com.example.squad03.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Schema(description = "Dados de retorno de um Agregado")
public class AgregadoResponseDTO {

    @Schema(description = "ID do agregado", example = "1")
    private Long idAgregado;

    @Schema(description = "Função do colaborador no contrato", example = "Coordenador de Projeto")
    private String funcao;

    @Schema(description = "Data de início da atuação como agregado", example = "2025-06-01")
    private LocalDate dataInicio;

    @Schema(description = "Data de término da atuação (se aplicável)", example = "2025-12-31")
    private LocalDate dataFim;

    @Schema(description = "Data/hora em que o registro foi criado", example = "2025-05-29T14:30:00")
    private LocalDateTime criadoEm;

    @Schema(description = "Dados básicos do contrato associado")
    private ContratoSimplesDTO contrato;

    @Schema(description = "Dados básicos do colaborador agregado")
    private ColaboradorSimplesDTO colaborador;
}
