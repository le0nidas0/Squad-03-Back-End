package com.example.squad03.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Schema(description = "Dados básicos de um contrato (para identificar o contrato original)")
public class ContratoSimplesDTO {

    @Schema(description = "ID do contrato", example = "42")
    private Long idContrato;

    @Schema(description = "Número do contrato", example = "CT-2025-0001")
    private String numeroContrato;

    @Schema(description = "Data de assinatura do contrato", example = "2025-01-15")
    private LocalDate dataInicio;

    @Schema(description = "Objeto do contrato", example = "Prestação de serviços de TI")
    private String descricao; // ou um campo específico para “objeto”

    @Schema(description = "Parte contratante", example = "Empresa XYZ")
    private String parteContratante;

    @Schema(description = "Parte contratada", example = "Empresa ABC")
    private String parteContratada;
}