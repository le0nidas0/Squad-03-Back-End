package com.example.squad03.controller;

import com.example.squad03.dto.AditivoContractualCreateDTO;
import com.example.squad03.dto.AditivoContractualResponseDTO;
import com.example.squad03.service.AditivoContractualService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/aditivo-contratual")
@RequiredArgsConstructor
@Tag(name = "Aditivos Contratuais", description = "Operações relacionadas a aditivos contratuais")
public class AditivoContractualController {
    private final AditivoContractualService service;

    @Operation(summary = "Cria um novo aditivo contratual")
    @ApiResponse(responseCode = "201", description = "Aditivo criado com sucesso")
    @PostMapping
    public ResponseEntity<AditivoContractualResponseDTO> criar(
            @Valid @RequestBody AditivoContractualCreateDTO dto
    ) {
        AditivoContractualResponseDTO resp = service.criarAditivo(dto);
        return ResponseEntity.status(201).body(resp);
    }

    @Operation(summary = "Busca aditivo por ID")
    @GetMapping("/{id}")
    public ResponseEntity<AditivoContractualResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @Operation(summary = "Lista todos os aditivos")
    @GetMapping
    public ResponseEntity<List<AditivoContractualResponseDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @Operation(summary = "Atualiza um aditivo existente")
    @PutMapping("/{id}")
    public ResponseEntity<AditivoContractualResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody AditivoContractualCreateDTO dto
    ) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @Operation(summary = "Deleta um aditivo")
    @ApiResponse(responseCode = "204", description = "Aditivo deletado com sucesso")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
