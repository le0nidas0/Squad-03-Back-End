package com.example.squad03.controller;

import com.example.squad03.dto.AgregadoCreateDTO;
import com.example.squad03.dto.AgregadoResponseDTO;
import com.example.squad03.service.AgregadoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agregado")
@RequiredArgsConstructor
@Tag(name = "Agregado", description = "Operações relacionadas a Agregados em Contratos")
public class AgregadoController {

    private final AgregadoService service;

    @Operation(summary = "Cria um novo Agregado para um contrato")
    @ApiResponse(responseCode = "201", description = "Agregado criado com sucesso")
    @PostMapping
    public ResponseEntity<AgregadoResponseDTO> criar(
            @Valid @RequestBody AgregadoCreateDTO dto
    ) {
        AgregadoResponseDTO resp = service.criarAgregado(dto);
        return ResponseEntity.status(201).body(resp);
    }

    @Operation(summary = "Busca um Agregado por ID")
    @ApiResponse(responseCode = "200", description = "Agregado retornado com sucesso")
    @GetMapping("/{id}")
    public ResponseEntity<AgregadoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @Operation(summary = "Lista todos os Agregados de um contrato")
    @ApiResponse(responseCode = "200", description = "Lista de Agregados retornada com sucesso")
    @GetMapping("/contrato/{contratoId}")
    public ResponseEntity<List<AgregadoResponseDTO>> listarPorContrato(@PathVariable Long contratoId) {
        return ResponseEntity.ok(service.listarPorContrato(contratoId));
    }

    @Operation(summary = "Lista todos os Agregados de um colaborador")
    @ApiResponse(responseCode = "200", description = "Lista de Agregados retornada com sucesso")
    @GetMapping("/colaborador/{colaboradorId}")
    public ResponseEntity<List<AgregadoResponseDTO>> listarPorColaborador(@PathVariable Long colaboradorId) {
        return ResponseEntity.ok(service.listarPorColaborador(colaboradorId));
    }

    @Operation(summary = "Atualiza um Agregado existente")
    @ApiResponse(responseCode = "200", description = "Agregado atualizado com sucesso")
    @PutMapping("/{id}")
    public ResponseEntity<AgregadoResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody AgregadoCreateDTO dto
    ) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @Operation(summary = "Deleta um Agregado")
    @ApiResponse(responseCode = "204", description = "Agregado deletado com sucesso")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
