package com.example.squad03.service;

import com.example.squad03.dto.EmpresaCreateDTO;
import com.example.squad03.dto.EmpresaResponseDTO;

import java.util.List;

public interface EmpresaService {
    EmpresaResponseDTO criarOrgao(EmpresaCreateDTO dto);
    EmpresaResponseDTO buscarPorId(Long id);
    List<EmpresaResponseDTO> listarTodos();
    String deletar(Long id);
    EmpresaResponseDTO atualizar(Long id, EmpresaCreateDTO dto);
}