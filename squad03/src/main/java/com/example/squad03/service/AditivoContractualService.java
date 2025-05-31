package com.example.squad03.service;

import com.example.squad03.dto.AditivoContractualCreateDTO;
import com.example.squad03.dto.AditivoContractualResponseDTO;

import java.util.List;

public interface AditivoContractualService {

    AditivoContractualResponseDTO criarAditivo(AditivoContractualCreateDTO dto);

    AditivoContractualResponseDTO buscarPorId(Long id);

    List<AditivoContractualResponseDTO> listarPorContrato(Long contratoId);

    AditivoContractualResponseDTO atualizar(Long id, AditivoContractualCreateDTO dto);

    void deletar(Long id);
}