package com.example.squad03.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Schema(description = "Dados de retorno de um Órgão Contratante (seguindo a LGPD)")
public class EmpresaResponseDTO {

    @Schema(description = "ID do órgão contratante", example = "1")
    private Long idOrgao;

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

    @NotBlank
    @Schema(description = "Inscricao municipal")
    private String inscricaoMunicipal;

    @NotBlank
    @Schema(description = "Tipo de empresa", example = "Publica ou privada")
    private String tipoEmpresa;

    @NotBlank
    @Schema(description = "CEP", example = "49000000")
    private String cep;

    @NotBlank
    @Schema(description = "Bairro", example = "Atalaia")
    private String bairro;

    @NotBlank
    @Schema(description = "logradouro", example = "Avenida Ivo do Prado")
    private String logradouro;

    @Schema(description = "Complemento do endereço")
    private String complemento;

    @NotBlank
    @Schema(description = "email da empresa", example = "getinfo@gmail.com")
    private String email;

    @NotBlank
    @Schema(description = "telefone da empresa", example = "(79)9 9999-9999")
    private String telefone;

    private List<RepresentanteResponseDTO> representantes;

}
