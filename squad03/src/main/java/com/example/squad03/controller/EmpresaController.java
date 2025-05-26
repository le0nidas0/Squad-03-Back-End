package com.example.squad03.controller;

import com.example.squad03.dto.EmpresaCreateDTO;
import com.example.squad03.dto.EmpresaResponseDTO;

import com.example.squad03.service.EmpresaService;
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
@RequestMapping("/api/empresa")
@RequiredArgsConstructor
@Tag(name = "Empresa", description = "Operações relacionadas a empresa")
public class EmpresaController {

    private final EmpresaService service;

    @Operation(summary = "Cria uma nova empresa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Empresa criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    public ResponseEntity<EmpresaResponseDTO> criar(@Valid @RequestBody EmpresaCreateDTO dto) {
        EmpresaResponseDTO response = service.criarOrgao(dto);
        return ResponseEntity.status(201).body(response);
    }

    @Operation(summary = "Busca uma empresa por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "empresa encontrado"),
            @ApiResponse(responseCode = "404", description = "empresa não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EmpresaResponseDTO> buscarPorId(@PathVariable Long id){
        EmpresaResponseDTO response = service.buscarPorId(id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Lista todos as empresas")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    @GetMapping
    public ResponseEntity<List<EmpresaResponseDTO>> listarTodos(){
        List<EmpresaResponseDTO> lista = service.listarTodos();
        return ResponseEntity.ok(lista);
    }

    @Operation(summary = "Atualiza uma empresa contratante existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Órgão atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Órgão não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<EmpresaResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody EmpresaCreateDTO dto) {
        EmpresaResponseDTO response = service.atualizar(id, dto);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Deletar uma empresa por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Empresa deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Empresa não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<EmpresaResponseDTO> deletar(@PathVariable Long id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}