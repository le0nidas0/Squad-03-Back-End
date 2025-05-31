package com.example.squad03.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Dados básicos de um Colaborador (para Agregado)")
public class ColaboradorSimplesDTO {

    @Schema(description = "ID do colaborador", example = "5")
    private Long idFuncionario;

    @Schema(description = "Nome completo do colaborador", example = "João Silva")
    private String nome;

    @Schema(description = "CPF do colaborador", example = "123.456.789-00")
    private String cpf;

    @Schema(description = "Cargo ou função principal do colaborador", example = "Analista de Sistemas")
    private String cargo;
}
