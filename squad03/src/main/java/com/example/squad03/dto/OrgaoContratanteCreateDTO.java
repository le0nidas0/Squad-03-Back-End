package com.example.squad03.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Dados para criação de um Órgão Contratante")
public class OrgaoContratanteCreateDTO {

    @NotBlank
    @Schema(description = "Nome do órgão contratante", example = "Prefeitura Municipal de Recife")
    private String nome;

    @NotBlank
    @Schema(description = "CNPJ do órgão contratante", example = "12.345.678/0001-90")
    private String cnpj;
}
