package com.example.squad03.controller;

import com.example.squad03.dto.RepresentanteCreateDTO;
import com.example.squad03.dto.RepresentanteResponseDTO;
import com.example.squad03.service.RepresentanteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/representantes")
@RequiredArgsConstructor
@Tag(name = "Representantes", description = "Operações relacionadas aos representantes dos orgãos")
public class RepresentanteController {

    private final RepresentanteService service;

    @Operation(summary = "Cria um novo representante")
    @PostMapping
    public RepresentanteResponseDTO criar(@RequestBody @Valid RepresentanteCreateDTO dto) {
        return service.criar(dto);
    }

    @Operation(summary = "Lista todos os representantes")
    @GetMapping
    public List<RepresentanteResponseDTO> listar() {
        return service.listar();
    }

    @Operation(summary = "Busca representante por ID")
    @GetMapping("/{id}")
    public RepresentanteResponseDTO buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @Operation(summary = "Lista representantes por ID do órgão")
    @GetMapping("/orgaos/{idOrgao}")
    public List<RepresentanteResponseDTO> buscarPorOrgaoId(@PathVariable Long idOrgao) {
        return service.buscarPorOrgaoId(idOrgao);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}
