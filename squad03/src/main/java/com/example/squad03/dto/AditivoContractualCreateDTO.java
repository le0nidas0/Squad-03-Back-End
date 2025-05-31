package com.example.squad03.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Schema(description = "Dados para criação de um Aditivo Contratual")
public class AditivoContractualCreateDTO {

    @NotNull
    @Schema(description = "Tipo do aditivo", example = "PRORROGACAO")
    private String tipo;

    @NotNull
    @Schema(description = "Mudanças específicas (descrição)", example = "Alteração de escopo para inclusão de serviços X")
    private String descricaoMudancas;

    @NotNull
    @Schema(description = "Justificativa do aditivo", example = "Adaptação de cronograma devido a imprevistos")
    private String justificativa;

    @NotNull
    @Schema(description = "Data de vigência do aditivo", example = "2025-07-01")
    private LocalDate dataVigencia;

    @NotNull
    @Schema(description = "ID do contrato original ao qual este aditivo se refere")
    private Long contratoId;
}