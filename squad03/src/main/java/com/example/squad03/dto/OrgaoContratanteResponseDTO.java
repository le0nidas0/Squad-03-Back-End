package com.example.squad03.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Dados de retorno de um Órgão Contratante")
public class OrgaoContratanteResponseDTO {

    @Schema(description = "ID do órgão contratante", example = "1")
    private Long id;

    @Schema(description = "Nome do órgão contratante", example = "Prefeitura Municipal de Recife")
    private String nome;

    @Schema(description = "CNPJ do órgão contratante", example = "12.345.678/0001-90")
    private String cnpj;
}
