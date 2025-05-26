package com.example.squad03.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Schema(description = "Dados de resposta de um aditivo contratual")
public class AditivoContractualResponseDTO {

    @Schema(description = "ID do aditivo", example = "1")
    private Long idAditivoContractual;

    @Schema(description = "Tipo do aditivo", example = "REAJUSTE")
    private String tipo;

    @Schema(description = "Justificativa do aditivo")
    private String justificativa;

    @Schema(description = "Data de criação do aditivo", example = "2025-05-26T08:34:10")
    private LocalDateTime criadoEm;

    @Schema(description = "Dados do contrato associado")
    private ContratoResponseDTO contrato;

    @Schema(description = "Dados do responsável pelo aditivo")
    private ColaboradorResponseDTO responsavel;
}
