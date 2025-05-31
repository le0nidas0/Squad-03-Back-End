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
@RequestMapping("/api/aditivo")
@RequiredArgsConstructor
@Tag(name = "Aditivo Contratual", description = "Operações relacionadas aos aditivos contratuais")
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
    @ApiResponse(responseCode = "200", description = "Aditivo retornado com sucesso")
    @GetMapping("/{id}")
    public ResponseEntity<AditivoContractualResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @Operation(summary = "Lista aditivos por contrato")
    @ApiResponse(responseCode = "200", description = "Lista de aditivos retornada com sucesso")
    @GetMapping("/contrato/{idContrato}")
    public ResponseEntity<List<AditivoContractualResponseDTO>> listarPorContrato(
            @PathVariable Long idContrato
    ) {
        return ResponseEntity.ok(service.listarPorContrato(idContrato));
    }

    @Operation(summary = "Atualiza um aditivo contratual")
    @ApiResponse(responseCode = "200", description = "Aditivo atualizado com sucesso")
    @PutMapping("/{id}")
    public ResponseEntity<AditivoContractualResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody AditivoContractualCreateDTO dto
    ) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @Operation(summary = "Deleta um aditivo contratual")
    @ApiResponse(responseCode = "204", description = "Aditivo deletado com sucesso")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
