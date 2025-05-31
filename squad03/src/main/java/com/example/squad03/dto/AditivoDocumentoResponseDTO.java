package com.example.squad03.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Schema(description = "Dados de retorno de um Documento de Aditivo Contratual")
public class AditivoDocumentoResponseDTO {

    @Schema(description = "ID do documento", example = "100")
    private Long idDocumento;

    @Schema(description = "Nome original do arquivo", example = "aditivo123.pdf")
    private String nomeArquivo;

    @Schema(description = "Tipo MIME do arquivo", example = "application/pdf")
    private String mimeType;

    @Schema(description = "Tamanho em bytes", example = "45000")
    private Long tamanho;

    @Schema(description = "Data de criação do documento", example = "2025-05-29T15:30:00")
    private LocalDateTime criadoEm;
}