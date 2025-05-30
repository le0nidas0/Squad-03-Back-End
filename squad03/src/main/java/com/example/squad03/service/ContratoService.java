package com.example.squad03.service;

import com.example.squad03.dto.ContratoCreateDTO;
import com.example.squad03.dto.ContratoResponseDTO;
import com.example.squad03.enums.StatusContrato;

import java.util.List;

public interface ContratoService {

    ContratoResponseDTO criarContrato(ContratoCreateDTO dto);

    ContratoResponseDTO buscarPorId(Long id);

    List<ContratoResponseDTO> listarTodos();

    ContratoResponseDTO atualizar(Long id, ContratoCreateDTO dto);

    List<ContratoResponseDTO> buscarPorStatus(StatusContrato status);

    List<ContratoResponseDTO> listarContratosArquivados();

    List<ContratoResponseDTO> listarContratosNaoArquivados();

    void arquivarContrato(Long id);

    void deletar(Long id);
}