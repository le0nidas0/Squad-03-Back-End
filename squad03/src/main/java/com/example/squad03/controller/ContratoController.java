package com.example.squad03.controller;

import com.example.squad03.dto.ContratoCreateDTO;
import com.example.squad03.dto.ContratoResponseDTO;
import com.example.squad03.enums.StatusContrato;
import com.example.squad03.service.ContratoService;
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
@RequestMapping("/api/contrato")
@RequiredArgsConstructor
@Tag(name = "Contratos", description = "Operações relacionadas a contratos")
public class ContratoController {

    private final ContratoService service;

    @Operation(summary = "Cria um novo contrato")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Contrato criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    public ResponseEntity<ContratoResponseDTO> criar(
            @Valid @RequestBody ContratoCreateDTO dto
    ) {
        ContratoResponseDTO response = service.criarContrato(dto);
        return ResponseEntity.status(201).body(response);
    }

    @Operation(summary = "Buscar contrato por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Contrato encontrado"),
            @ApiResponse(responseCode = "404", description = "Contrato não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ContratoResponseDTO> buscarPorId(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @Operation(summary = "Lista todos os contratos")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    @GetMapping
    public ResponseEntity<List<ContratoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @Operation(summary = "Atualiza um contrato existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Contrato atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Contrato não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ContratoResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody ContratoCreateDTO dto
    ) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @Operation(summary = "Deleta um contrato")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Contrato deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Contrato não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Arquiva um contrato")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Contrato arquivado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Contrato não encontrado")
    })
    @PatchMapping("/{id}/arquivar")
    public ResponseEntity<Void> arquivarContrato(@PathVariable Long id) {
        service.arquivarContrato(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Lista contratos por status")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Contratos filtrados por status retornados com sucesso"),
            @ApiResponse(responseCode = "400", description = "Status inválido")
    })
    @GetMapping("/status/{status}")
    public ResponseEntity<List<ContratoResponseDTO>> buscarPorStatus(@PathVariable StatusContrato status) {
        return ResponseEntity.ok(service.buscarPorStatus(status));
    }

    @Operation(summary = "Lista contratos arquivados")
    @ApiResponse(responseCode = "200", description = "Lista de contratos arquivados retornada com sucesso")
    @GetMapping("/arquivados")
    public ResponseEntity<List<ContratoResponseDTO>> listarArquivados() {
        return ResponseEntity.ok(service.listarContratosArquivados());
    }

    @Operation(summary = "Lista contratos não arquivados")
    @ApiResponse(responseCode = "200", description = "Lista de contratos não arquivados retornada com sucesso")
    @GetMapping("/nao-arquivados")
    public ResponseEntity<List<ContratoResponseDTO>> listarNaoArquivados() {
        return ResponseEntity.ok(service.listarContratosNaoArquivados());
    }
}