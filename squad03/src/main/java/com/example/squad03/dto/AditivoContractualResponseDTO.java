package com.example.squad03.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Schema(description = "Dados de retorno de um Aditivo Contratual")
public class AditivoContractualResponseDTO {

    @Schema(description = "ID do aditivo", example = "1")
    private Long idAditivoContractual;

    @Schema(description = "Tipo do aditivo", example = "PRORROGACAO")
    private String tipo;

    @Schema(description = "Mudanças específicas", example = "Alteração de escopo para inclusão de serviços X")
    private String descricaoMudancas;

    @Schema(description = "Justificativa do aditivo")
    private String justificativa;

    @Schema(description = "Data de vigência do aditivo", example = "2025-07-01")
    private LocalDate dataVigencia;

    @Schema(description = "Data de criação do aditivo", example = "2025-05-29T14:00:00")
    private LocalDateTime criadoEm;

    @Schema(description = "Dados do contrato associado")
    private ContratoSimplesDTO contrato;

    @Schema(description = "Lista de documentos anexados a este aditivo")
    private List<AditivoDocumentoResponseDTO> documentos;
}