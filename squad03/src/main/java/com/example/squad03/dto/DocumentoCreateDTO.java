package com.example.squad03.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Dados para criação de um Documento")
public class DocumentoCreateDTO {

    @NotNull
    @Schema(description = "Nome do arquivo", example = "proposta.pdf")
    private String nomeArquivo;

    @NotNull
    @Schema(description = "URL onde o arquivo está armazenado", example = "https://bucket.s3/.../proposta.pdf")
    private String url;

    @Schema(description = "Tipo/MIME do arquivo", example = "application/pdf")
    private String tipo;

    @Schema(description = "Tamanho do arquivo em bytes", example = "34567")
    private Long tamanho;

    @NotNull
    @Schema(description = "ID do contrato ao qual este documento pertence")
    private Long contratoId;
}
