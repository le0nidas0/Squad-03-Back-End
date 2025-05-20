package com.example.squad03.service;

import com.example.squad03.dto.RepresentanteCreateDTO;
import com.example.squad03.dto.RepresentanteResponseDTO;

import java.util.List;

public interface RepresentanteService {
    RepresentanteResponseDTO criar(RepresentanteCreateDTO dto);
    List<RepresentanteResponseDTO> listar();
    RepresentanteResponseDTO buscarPorId(Long id);

    String deletar(Long id);
}
