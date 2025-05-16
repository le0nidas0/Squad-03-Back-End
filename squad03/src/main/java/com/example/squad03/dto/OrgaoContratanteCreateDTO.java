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
    @Schema(description = "Nome do órgão contratante", example = "Prefeitura Municipal de Sergipe")
    private String nome;

    @NotBlank
    @Schema(description = "Nome fantasia do órgão contratante", example = "Prefeitura de Sergipe")
    private String nomeFantasia;

    @NotBlank
    @Schema(description = "Razão social do órgão contratante", example = "Município de Sergipe")
    private String razaoSocial;

    @NotBlank
    @Schema(description = "CNPJ do órgão contratante", example = "12.345.678/0001-90")
    private String cnpj;

    @NotBlank
    @Schema(description = "Número da empresa", example = "123456789")
    private String numeroEmpresa;

    @NotBlank
    @Schema(description = "Estado", example = "Sergipe")
    private String estado;

    @NotBlank
    @Schema(description = "Cidade", example = "Aracaju")
    private String cidade;

}