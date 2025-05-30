package com.example.squad03.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Dados para anexar um novo documento (PDF) a um contrato")
public class DocumentoCreateDTO {

    @NotNull
    @Schema(description = "ID do contrato ao qual este documento pertence")
    private Long contratoId;
}