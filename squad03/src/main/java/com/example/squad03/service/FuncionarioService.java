package com.example.squad03.service;

import com.example.squad03.dto.FuncionarioCreateDTO;
import com.example.squad03.dto.FuncionarioResponseDTO;

import java.util.List;

public interface FuncionarioService {

    FuncionarioResponseDTO criar(FuncionarioCreateDTO dto);

    List<FuncionarioResponseDTO> listarTodos();

    FuncionarioResponseDTO buscarPorId(Long id);

    void deletar(Long id);

    FuncionarioResponseDTO atualizar(Long id, FuncionarioCreateDTO dto);
}