package com.example.squad03.service;

import com.example.squad03.dto.EntregavelCreateDTO;
import com.example.squad03.dto.EntregavelResponseDTO;

import java.util.List;

public interface EntregavelService {
    EntregavelResponseDTO criarEntregavel(EntregavelCreateDTO dto);
    List<EntregavelResponseDTO> listarTodos();
    EntregavelResponseDTO buscarPorId(Long id);
    EntregavelResponseDTO atualizar(Long id, EntregavelCreateDTO dto);
    void deletar(Long id);
}
