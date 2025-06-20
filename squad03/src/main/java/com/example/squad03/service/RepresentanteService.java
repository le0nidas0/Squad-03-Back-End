package com.example.squad03.service;

import com.example.squad03.dto.RepresentanteCreateDTO;
import com.example.squad03.dto.RepresentanteResponseDTO;

import java.util.List;

public interface RepresentanteService {
    RepresentanteResponseDTO criar(RepresentanteCreateDTO dto);
    List<RepresentanteResponseDTO> listar();
    RepresentanteResponseDTO buscarPorId(Long id);
    List<RepresentanteResponseDTO> buscarPorOrgaoId(Long idOrgao);

    String deletar(Long id);
}
