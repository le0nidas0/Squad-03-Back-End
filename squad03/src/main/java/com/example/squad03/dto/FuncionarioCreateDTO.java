package com.example.squad03.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Dados para criação de um Funcionário")
public class FuncionarioCreateDTO {

    @NotBlank
    @Schema(description = "Nome do funcionário", example = "João da Silva")
    private String nome;

    @NotBlank
    @Schema(description = "Cargo ocupado pelo funcionário", example = "Gerente de Contratos")
    private String cargo;

    @NotBlank
    @Schema(description = "Telefone do funcionário (não exposto ao front)", example = "(81) 99999-9999", accessMode = Schema.AccessMode.WRITE_ONLY)
    private String telefone;

    @NotBlank
    @Schema(description = "E-mail do funcionário (não exposto ao front)", example = "joao.silva@empresa.com", accessMode = Schema.AccessMode.WRITE_ONLY)
    private String email;

    @NotBlank
    @Schema(description = "CPF do funcionário (não exposto ao front)", example = "123.456.789-00", accessMode = Schema.AccessMode.WRITE_ONLY)
    private String cpf;
}
