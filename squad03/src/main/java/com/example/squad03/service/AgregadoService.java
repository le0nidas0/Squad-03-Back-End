package com.example.squad03.service;

import com.example.squad03.dto.AgregadoCreateDTO;
import com.example.squad03.dto.AgregadoResponseDTO;

import java.util.List;

public interface AgregadoService {

    AgregadoResponseDTO criarAgregado(AgregadoCreateDTO dto);

    AgregadoResponseDTO buscarPorId(Long id);

    List<AgregadoResponseDTO> listarPorContrato(Long contratoId);

    List<AgregadoResponseDTO> listarPorColaborador(Long colaboradorId);

    AgregadoResponseDTO atualizar(Long id, AgregadoCreateDTO dto);

    void deletar(Long id);
}
