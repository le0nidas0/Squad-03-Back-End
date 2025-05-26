package com.example.squad03.controller;

import com.example.squad03.dto.EntregavelCreateDTO;
import com.example.squad03.dto.EntregavelResponseDTO;
import com.example.squad03.service.EntregavelService;
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
@RequestMapping("/api/entregaveis")
@RequiredArgsConstructor
@Tag(name = "Entregáveis", description = "Operações relacionadas a entregáveis")
public class EntregavelController {

    private final EntregavelService service;

    @Operation(summary = "Cria um novo entregável")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Entregável criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    public ResponseEntity<EntregavelResponseDTO> criar(@Valid @RequestBody EntregavelCreateDTO dto) {
        EntregavelResponseDTO criado = service.criarEntregavel(dto);
        return ResponseEntity.status(201).body(criado);
    }

    @Operation(summary = "Lista todos os entregáveis")
    @GetMapping
    public ResponseEntity<List<EntregavelResponseDTO>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @Operation(summary = "Busca entregável por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Entregável encontrado"),
            @ApiResponse(responseCode = "404", description = "Entregável não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EntregavelResponseDTO> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @Operation(summary = "Atualiza um entregável existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Entregável atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Entregável não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<EntregavelResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody EntregavelCreateDTO dto
    ) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @Operation(summary = "Deleta um entregável")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Entregável deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Entregável não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
