package com.example.squad03.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Dados para criação de um aditivo contratual")
public class AditivoContractualCreateDTO {

    @NotNull
    @Schema(description = "Tipo do aditivo", example = "REAJUSTE")
    private String tipo;

    @NotNull
    @Schema(description = "Justificativa do aditivo", example = "Prorrogação de prazo devido a …")
    private String justificativa;

    @NotNull
    @Schema(description = "ID do contrato ao qual este aditivo pertence")
    private Long contratoId;

    @NotNull
    @Schema(description = "ID do colaborador responsável pelo aditivo")
    private Long responsavelId;
}
