package com.example.squad03.controller;

import com.example.squad03.dto.ContratoCreateDTO;
import com.example.squad03.dto.ContratoResponseDTO;
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
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Contrato criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    public ResponseEntity<ContratoResponseDTO> criar(@Valid @RequestBody ContratoCreateDTO dto){
        ContratoResponseDTO response = service.criarContrato(dto);
        return ResponseEntity.status(201).body(response);
    }

    @Operation(summary = "Buscar contrato por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contrato encontrado"),
            @ApiResponse(responseCode = "404", description = "Contrato não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ContratoResponseDTO> buscarPorId(@PathVariable Long id){
        ContratoResponseDTO response = service.buscarPorId(id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Lista todos os contratos")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    @GetMapping
    public ResponseEntity<List<ContratoResponseDTO>> listarTodos(){
        List<ContratoResponseDTO> lista = service.listarTodos();
        return ResponseEntity.ok(lista);
    }

    @Operation(summary = "Atualiza um contrato existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contrato atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Contrato não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ContratoResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody ContratoCreateDTO dto) {
        ContratoResponseDTO response = service.atualizar(id, dto);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Contrato arquivado com sucesso")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Contrato arquivado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Contrato não encontrado")
    })
    @PatchMapping("/{id}/arquivar")
    public ResponseEntity<Void> arquivarContrato(@PathVariable Long id) {
        service.arquivarContrato(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/arquivados")
    @Operation(summary = "Listar contratos arquivados")
    public ResponseEntity<List<ContratoResponseDTO>> listarArquivados() {
        List<ContratoResponseDTO> dtos = service.listarContratosArquivados();
        return ResponseEntity.ok(dtos);
    }

}
