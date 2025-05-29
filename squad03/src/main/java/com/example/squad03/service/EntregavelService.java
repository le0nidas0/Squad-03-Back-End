package com.example.squad03.service;

import com.example.squad03.dto.EntregavelCreateDTO;
import com.example.squad03.dto.EntregavelResponseDTO;

import java.util.List;

public interface EntregavelService {
    EntregavelResponseDTO criarEntregavel(EntregavelCreateDTO dto);
    List<EntregavelResponseDTO> listarTodos();
    EntregavelResponseDTO buscarPorId(Long id);
    List<EntregavelResponseDTO> buscarPorContratoId(Long contratoId);
    EntregavelResponseDTO atualizar(Long id, EntregavelCreateDTO dto);
    List<EntregavelResponseDTO> buscarPorResponsavelId(Long responsavelId);
    void deletar(Long id);
}
