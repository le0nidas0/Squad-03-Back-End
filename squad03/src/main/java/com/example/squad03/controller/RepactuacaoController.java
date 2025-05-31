package com.example.squad03.controller;

import com.example.squad03.dto.RepactuacaoCreateDTO;
import com.example.squad03.dto.RepactuacaoResponseDTO;
import com.example.squad03.service.RepactuacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/repactuacao")
@RequiredArgsConstructor
@Tag(name = "Repactuação", description = "Operações relacionadas a Repactuações em Contratos")
public class RepactuacaoController {

    private final RepactuacaoService service;

    @Operation(summary = "Cria uma nova repactuação para um contrato")
    @ApiResponse(responseCode = "201", description = "Repactuação criada com sucesso")
    @PostMapping
    public ResponseEntity<RepactuacaoResponseDTO> criar(
            @Valid @RequestBody RepactuacaoCreateDTO dto
    ) {
        RepactuacaoResponseDTO resp = service.criarRepactuacao(dto);
        return ResponseEntity.status(201).body(resp);
    }

    @Operation(summary = "Busca repactuação por ID")
    @ApiResponse(responseCode = "200", description = "Repactuação retornada com sucesso")
    @GetMapping("/{id}")
    public ResponseEntity<RepactuacaoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @Operation(summary = "Lista todas as repactuações de um contrato")
    @ApiResponse(responseCode = "200", description = "Lista de repactuações retornada com sucesso")
    @GetMapping("/contrato/{idContrato}")
    public ResponseEntity<List<RepactuacaoResponseDTO>> listarPorContrato(
            @PathVariable Long idContrato
    ) {
        return ResponseEntity.ok(service.listarPorContrato(idContrato));
    }

    @Operation(summary = "Atualiza uma repactuação existente")
    @ApiResponse(responseCode = "200", description = "Repactuação atualizada com sucesso")
    @PutMapping("/{id}")
    public ResponseEntity<RepactuacaoResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody RepactuacaoCreateDTO dto
    ) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @Operation(summary = "Deleta uma repactuação")
    @ApiResponse(responseCode = "204", description = "Repactuação deletada com sucesso")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
