package com.example.squad03.mapper;

import com.example.squad03.dto.ContratoCreateDTO;
import com.example.squad03.dto.ContratoResponseDTO;
import com.example.squad03.enums.StatusContrato;
import com.example.squad03.model.Contrato;
import com.example.squad03.model.Funcionario;
import com.example.squad03.model.OrgaoContratante;


import java.time.LocalDateTime;

public class ContratoMapper {

    public static Contrato toEntity(ContratoCreateDTO dto, OrgaoContratante orgao, Funcionario responsavel, Funcionario representante) {
        Contrato contrato = new Contrato();
        contrato.setPrazo(dto.getPrazo());
        contrato.setValor(dto.getValor());

        contrato.setStatus(dto.getStatus() != null ? dto.getStatus() : StatusContrato.ATIVO);

        contrato.setCriadoEm(LocalDateTime.now());
        contrato.setOrgaoContratante(orgao);
        contrato.setResponsavel(responsavel);
        contrato.setRepresentante(representante);
        return contrato;
    }

    public static ContratoResponseDTO toDTO(Contrato contrato) {
        ContratoResponseDTO dto = new ContratoResponseDTO();
        dto.setIdContrato(contrato.getIdContrato());
        dto.setPrazo(contrato.getPrazo());
        dto.setValor(contrato.getValor());
        dto.setStatus(contrato.getStatus()); // agora retorna um enum
        dto.setCriadoEm(contrato.getCriadoEm());
        return dto;
    }
}
