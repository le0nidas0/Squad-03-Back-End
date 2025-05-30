package com.example.squad03.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Schema(description = "Dados de retorno de um Documento armazenado como BLOB")
public class DocumentoResponseDTO {

    @Schema(description = "ID do documento", example = "1")
    private Long idDocumento;

    @Schema(description = "Nome original do arquivo", example = "proposta.pdf")
    private String nomeArquivo;

    @Schema(description = "Tipo MIME do arquivo", example = "application/pdf")
    private String mimeType;

    @Schema(description = "Tamanho em bytes", example = "34567")
    private Long tamanho;

    @Schema(description = "Data de criação do documento", example = "2025-05-29T11:00:00")
    private LocalDateTime criadoEm;

    @Schema(description = "ID do contrato associado", example = "42")
    private Long contratoId;
}