package com.example.squad03.service;

import com.example.squad03.dto.RepactuacaoCreateDTO;
import com.example.squad03.dto.RepactuacaoResponseDTO;

import java.util.List;

public interface RepactuacaoService {

    RepactuacaoResponseDTO criarRepactuacao(RepactuacaoCreateDTO dto);

    RepactuacaoResponseDTO buscarPorId(Long id);

    List<RepactuacaoResponseDTO> listarPorContrato(Long idContrato);

    RepactuacaoResponseDTO atualizar(Long id, RepactuacaoCreateDTO dto);

    void deletar(Long id);
}
