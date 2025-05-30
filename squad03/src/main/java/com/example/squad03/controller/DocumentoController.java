package com.example.squad03.controller;

import com.example.squad03.dto.DocumentoResponseDTO;
import com.example.squad03.service.DocumentoService;
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
@RequestMapping("/api/documento")
@RequiredArgsConstructor
@Tag(name = "Documentos", description = "Operações relacionadas a documentos anexados a contratos")
public class DocumentoController {

    private final DocumentoService service;

    @Operation(summary = "Anexa um PDF a um contrato")
    @ApiResponse(responseCode = "201", description = "Documento anexado com sucesso")
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<DocumentoResponseDTO> upload(
            @RequestParam @NotNull Long contratoId,
            @RequestPart("file") MultipartFile file
    ) {
        DocumentoResponseDTO dto = service.anexarDocumento(contratoId, file);
        return ResponseEntity.status(201).body(dto);
    }

    @Operation(summary = "Download de um PDF anexado")
    @ApiResponse(responseCode = "200", description = "PDF retornado com sucesso")
    @GetMapping("/{id}/download")
    public ResponseEntity<byte[]> download(@PathVariable Long id) {
        // Metadados (nome, mime)
        DocumentoResponseDTO meta = service.buscarPorId(id);
        // Conteúdo bruto
        byte[] dados = service.buscarDados(id);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(meta.getMimeType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + meta.getNomeArquivo() + "\"")
                .body(dados);
    }

    @Operation(summary = "Lista documentos de um contrato")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    @GetMapping("/contrato/{contratoId}")
    public ResponseEntity<List<DocumentoResponseDTO>> listar(
            @PathVariable Long contratoId
    ) {
        List<DocumentoResponseDTO> lista = service.listarPorContrato(contratoId);
        return ResponseEntity.ok(lista);
    }

    @Operation(summary = "Deleta um documento")
    @ApiResponse(responseCode = "204", description = "Documento deletado com sucesso")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
