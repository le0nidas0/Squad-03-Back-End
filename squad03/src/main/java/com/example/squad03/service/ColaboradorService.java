package com.example.squad03.service;

import com.example.squad03.dto.ColaboradorCreateDTO;
import com.example.squad03.dto.ColaboradorResponseDTO;

import java.util.List;

public interface ColaboradorService {

    ColaboradorResponseDTO criar(ColaboradorCreateDTO dto);

    List<ColaboradorResponseDTO> listarTodos();

    ColaboradorResponseDTO buscarPorId(Long id);

    void deletar(Long id);

    ColaboradorResponseDTO atualizar(Long id, ColaboradorCreateDTO dto);
}