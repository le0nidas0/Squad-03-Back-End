package com.example.squad03.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Dados de retorno de um Funcionário")
public class FuncionarioResponseDTO {

    @Schema(description = "ID do funcionário", example = "1")
    private Long idFuncionario;

    @Schema(description = "Nome do funcionário", example = "João Silva")
    private String nome;

    @Schema(description = "Cargo do funcionário", example = "Gerente de Contratos")
    private String cargo;
}
