package com.example.squad03.controller;

import com.example.squad03.dto.DocumentoCreateDTO;
import com.example.squad03.dto.DocumentoResponseDTO;
import com.example.squad03.service.DocumentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/documento")
@RequiredArgsConstructor
@Tag(name = "Documentos", description = "Operações relacionadas a documentos de contrato")
public class DocumentoController {

    private final DocumentoService service;

    @Operation(summary = "Cria um novo documento para um contrato")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Documento criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Contrato não encontrado")
    })
    @PostMapping
    public ResponseEntity<DocumentoResponseDTO> criar(@Valid @RequestBody DocumentoCreateDTO dto) {
        DocumentoResponseDTO resp = service.criarDocumento(dto);
        return ResponseEntity.status(201).body(resp);
    }

    @Operation(summary = "Busca documento por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Documento encontrado"),
            @ApiResponse(responseCode = "404", description = "Documento não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<DocumentoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @Operation(summary = "Lista todos os documentos")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    @GetMapping
    public ResponseEntity<List<DocumentoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @Operation(summary = "Lista documentos de um contrato")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    @GetMapping("/contrato/{contratoId}")
    public ResponseEntity<List<DocumentoResponseDTO>> listarPorContrato(@PathVariable Long contratoId) {
        return ResponseEntity.ok(service.listarPorContrato(contratoId));
    }

    @Operation(summary = "Atualiza um documento existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Documento atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Documento ou contrato não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<DocumentoResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody DocumentoCreateDTO dto
    ) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @Operation(summary = "Deleta um documento")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Documento deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Documento não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
