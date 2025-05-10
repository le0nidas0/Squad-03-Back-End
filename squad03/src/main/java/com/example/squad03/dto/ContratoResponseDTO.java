package com.example.squad03.dto;

import com.example.squad03.enums.StatusContrato;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Schema(description = "Dados de retorno de um Contrato")
public class ContratoResponseDTO {

    @Schema(description = "ID do contrato", example = "1")
    private Long idContrato;

    @Schema(description = "Data de prazo do contrato", example = "2025-12-31")
    private LocalDate prazo;

    @Schema(description = "Valor total do contrato", example = "10000.00")
    private BigDecimal valor;

    @Schema(description = "Status atual do contrato", example = "ATIVO", allowableValues = {"ATIVO", "INATIVO", "ENCERRADO"})
    private StatusContrato status;

    @Schema(description = "Data de criação do contrato", example = "2025-01-01T12:00:00")
    private LocalDateTime criadoEm;

    @Schema(description = "Dados do órgão contratante")
    private OrgaoContratanteResponseDTO orgaoContratante;

    @Schema(description = "Dados do responsável pelo contrato")
    private FuncionarioResponseDTO responsavel;

}
