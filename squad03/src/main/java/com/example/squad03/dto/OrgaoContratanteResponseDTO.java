package com.example.squad03.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Dados de retorno de um Órgão Contratante (seguindo a LGPD)")
public class OrgaoContratanteResponseDTO {

    @Schema(description = "ID do órgão contratante", example = "1")
    private Long id;

    @Schema(description = "Nome do órgão contratante", example = "Prefeitura Municipal de Sergipe")
    private String nome;

    @Schema(description = "Nome fantasia do órgão contratante", example = "Prefeitura do Sergipe")
    private String nomeFantasia;

    @Schema(description = "Razão social do órgão contratante", example = "Prefeitura do Sergipe - Administração Direta")
    private String razaoSocial;

    @Schema(description = "Número da empresa", example = "12345")
    private String numeroEmpresa;

    @Schema(description = "Estado do órgão contratante", example = "SE")
    private String estado;

    @Schema(description = "Cidade do órgão contratante", example = "Sergipe")
    private String cidade;
}
