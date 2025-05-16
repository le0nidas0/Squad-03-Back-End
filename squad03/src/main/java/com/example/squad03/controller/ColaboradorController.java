package com.example.squad03.controller;

import com.example.squad03.dto.ColaboradorCreateDTO;
import com.example.squad03.dto.ColaboradorResponseDTO;
import com.example.squad03.service.ColaboradorService;
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
@RequestMapping("/api/colaboradores")
@RequiredArgsConstructor
@Tag(name = "Colaboradores", description = "Operações relacionadas aos colaboradores")
public class ColaboradorController {

    private final ColaboradorService service;

    @Operation(summary = "Cria um novo colaborador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "colaborador criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    public ResponseEntity<ColaboradorResponseDTO> criar(@Valid @RequestBody ColaboradorCreateDTO dto) {
        ColaboradorResponseDTO response = service.criar(dto);
        return ResponseEntity.status(201).body(response);
    }

    @Operation(summary = "Buscar colaborador por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "colaborador encontrado"),
            @ApiResponse(responseCode = "404", description = "colaborador não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ColaboradorResponseDTO> buscarPorId(@PathVariable Long id) {
        ColaboradorResponseDTO response = service.buscarPorId(id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Listar todos os colaboradores")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    @GetMapping
    public ResponseEntity<List<ColaboradorResponseDTO>> listarTodos() {
        List<ColaboradorResponseDTO> lista = service.listarTodos();
        return ResponseEntity.ok(lista);
    }

    @Operation(summary = "Atualizar um colaborador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "colaborador atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "colaborador não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ColaboradorResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody ColaboradorCreateDTO dto) {
        ColaboradorResponseDTO response = service.atualizar(id, dto);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Deletar um colaborador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "colaborador deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "colaborador não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
