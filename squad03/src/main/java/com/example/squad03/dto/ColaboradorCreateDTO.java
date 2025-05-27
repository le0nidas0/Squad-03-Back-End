package com.example.squad03.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Schema(description = "Dados para criação de um Funcionário")
public class ColaboradorCreateDTO {

    @NotBlank
    @Schema(description = "Nome do funcionário", example = "João Silva")
    private String nome;

    @NotBlank
    @Schema(description = "Email do funcionário", example = "joao.silva@email.com")
    private String email;

    @NotBlank
    @Schema(description = "CPF do funcionário", example = "123.456.789-00")
    private String cpf;

    @NotBlank
    @Schema(description = "Cargo do funcionário", example = "Gerente de Contratos")
    private String cargo;

    @NotBlank
    @Schema(description = "Telefone do funcionário", example = "(79) 91234-5678")
    private String telefone;

    @NotBlank
    @Schema(description = "Data de nascimento")
    private String dataNascimento;
}
