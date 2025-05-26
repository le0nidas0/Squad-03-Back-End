package com.example.squad03.mapper;

import com.example.squad03.dto.AditivoContractualCreateDTO;
import com.example.squad03.dto.AditivoContractualResponseDTO;
import com.example.squad03.model.AditivoContractual;
import com.example.squad03.model.Colaborador;
import com.example.squad03.model.Contrato;

import java.time.LocalDateTime;

public class AditivoContractualMapper {

    public static AditivoContractual toEntity(
            AditivoContractualCreateDTO dto,
            Contrato contrato,
            Colaborador responsavel
    ) {
        AditivoContractual entidade = new AditivoContractual();
        entidade.setTipo(dto.getTipo());
        entidade.setJustificativa(dto.getJustificativa());
        entidade.setCriadoEm(LocalDateTime.now());
        entidade.setContrato(contrato);
        entidade.setResponsavelId(responsavel.getIdFuncionario());
        return entidade;
    }

    public static AditivoContractualResponseDTO toDTO(AditivoContractual entidade, Colaborador responsavel) {
        AditivoContractualResponseDTO dto = new AditivoContractualResponseDTO();
        dto.setIdAditivoContractual(entidade.getIdAditivoContractual());
        dto.setTipo(entidade.getTipo());
        dto.setJustificativa(entidade.getJustificativa());
        dto.setCriadoEm(entidade.getCriadoEm());
        dto.setContrato(ContratoMapper.toDTO(entidade.getContrato()));
        dto.setResponsavel(ColaboradorMapper.toDTO(responsavel));
        return dto;
    }
}
