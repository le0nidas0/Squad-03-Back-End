package com.example.squad03.controller;

import com.example.squad03.dto.AditivoDocumentoCreateDTO;
import com.example.squad03.dto.AditivoDocumentoResponseDTO;
import com.example.squad03.service.AditivoDocumentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/aditivo/documento")
@RequiredArgsConstructor
@Tag(name = "Aditivo Documento", description = "Operações relacionadas aos documentos anexados a aditivos contratuais")
public class AditivoDocumentoController {

    private final AditivoDocumentoService service;

    @Operation(summary = "Anexa um documento a um aditivo contratual")
    @ApiResponse(responseCode = "201", description = "Documento anexado com sucesso")
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AditivoDocumentoResponseDTO> upload(
            @RequestParam @NotNull Long aditivoId,
            @RequestPart("file") MultipartFile file
    ) {
        AditivoDocumentoCreateDTO dto = new AditivoDocumentoCreateDTO();
        dto.setAditivoId(aditivoId);
        dto.setFile(file);
        AditivoDocumentoResponseDTO resp = service.anexarDocumento(dto);
        return ResponseEntity.status(201).body(resp);
    }

    @Operation(summary = "Lista documentos de um aditivo contratual")
    @ApiResponse(responseCode = "200", description = "Lista de documentos retornada com sucesso")
    @GetMapping("/aditivo/{aditivoId}")
    public ResponseEntity<List<AditivoDocumentoResponseDTO>> listarPorAditivo(
            @PathVariable Long aditivoId
    ) {
        return ResponseEntity.ok(service.listarPorAditivo(aditivoId));
    }

    @Operation(summary = "Download de um documento anexado a um aditivo")
    @ApiResponse(responseCode = "200", description = "Arquivo retornado com sucesso")
    @GetMapping("/{idDocumento}/download")
    public ResponseEntity<byte[]> download(@PathVariable Long idDocumento) {
        // Busca metadados pelo próprio idDocumento
        AditivoDocumentoResponseDTO meta = service.buscarPorId(idDocumento);

        // Busca array de bytes
        byte[] dados = service.baixarDocumento(idDocumento);

        // Retorna o arquivo com o MIME e nome corretos
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(meta.getMimeType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + meta.getNomeArquivo() + "\"")
                .body(dados);
    }

    @Operation(summary = "Deleta um documento de aditivo")
    @ApiResponse(responseCode = "204", description = "Documento deletado com sucesso")
    @DeleteMapping("/{idDocumento}")
    public ResponseEntity<Void> deletar(@PathVariable Long idDocumento) {
        service.deletar(idDocumento);
        return ResponseEntity.noContent().build();
    }
}
