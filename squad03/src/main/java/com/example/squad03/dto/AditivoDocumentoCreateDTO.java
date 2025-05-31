package com.example.squad03.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@Schema(description = "Dados para anexar um documento a um aditivo contratual")
public class AditivoDocumentoCreateDTO {

    @NotNull
    @Schema(description = "ID do aditivo ao qual este documento pertence")
    private Long aditivoId;

    @NotNull
    @Schema(description = "Arquivo a ser anexado (PDF, PNG, JPEG ou DOCX)")
    private MultipartFile file;
}