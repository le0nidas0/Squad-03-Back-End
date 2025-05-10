package com.example.squad03.service;

import com.example.squad03.dto.OrgaoContratanteCreateDTO;
import com.example.squad03.dto.OrgaoContratanteResponseDTO;

import java.util.List;

public interface OrgaoContratanteService {
    OrgaoContratanteResponseDTO criarOrgao(OrgaoContratanteCreateDTO dto);
    OrgaoContratanteResponseDTO buscarPorId(Long id);
    List<OrgaoContratanteResponseDTO> listarTodos();
    void deletar(Long id);
    OrgaoContratanteResponseDTO atualizar(Long id, OrgaoContratanteCreateDTO dto);
}