package com.example.squad03.controller;

import com.example.squad03.dto.OrgaoContratanteCreateDTO;
import com.example.squad03.dto.OrgaoContratanteResponseDTO;
import com.example.squad03.service.OrgaoContratanteService;
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
@RequestMapping("/api/orgao-contratante")
@RequiredArgsConstructor
@Tag(name = "Orgao Contratante", description = "Operações relacionadas ao orgao contratante")
public class OrgaoContratanteController {

    private final OrgaoContratanteService service;

    @Operation(summary = "Cria um novo órgão contratante")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Órgão contratante criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    public ResponseEntity<OrgaoContratanteResponseDTO> criar(@Valid @RequestBody OrgaoContratanteCreateDTO dto) {
        OrgaoContratanteResponseDTO response = service.criarOrgao(dto);
        return ResponseEntity.status(201).body(response);
    }

    @Operation(summary = "Busca um órgão contratante por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Órgão encontrado"),
            @ApiResponse(responseCode = "404", description = "Órgão não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<OrgaoContratanteResponseDTO> buscarPorId(@PathVariable Long id){
        OrgaoContratanteResponseDTO response = service.buscarPorId(id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Lista todos os órgãos contratantes")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    @GetMapping
    public ResponseEntity<List<OrgaoContratanteResponseDTO>> listarTodos(){
        List<OrgaoContratanteResponseDTO> lista = service.listarTodos();
        return ResponseEntity.ok(lista);
    }

    @Operation(summary = "Atualiza um órgão contratante existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Órgão atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Órgão não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<OrgaoContratanteResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody OrgaoContratanteCreateDTO dto) {
        OrgaoContratanteResponseDTO response = service.atualizar(id, dto);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Deletar um órgão contratante por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Órgão deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Órgão não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<OrgaoContratanteResponseDTO> deletar(@PathVariable Long id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}