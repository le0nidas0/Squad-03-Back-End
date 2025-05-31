package com.example.squad03.mapper;

import com.example.squad03.dto.*;
import com.example.squad03.model.AditivoContractual;

import java.util.stream.Collectors;

public class AditivoContractualMapper {

    public static AditivoContractualResponseDTO toDTO(AditivoContractual aditivo) {
        AditivoContractualResponseDTO dto = new AditivoContractualResponseDTO();
        dto.setIdAditivoContractual(aditivo.getIdAditivoContractual());
        dto.setTipo(aditivo.getTipo());
        dto.setDescricaoMudancas(aditivo.getDescricaoMudancas());
        dto.setJustificativa(aditivo.getJustificativa());
        dto.setDataVigencia(aditivo.getDataVigencia());
        dto.setCriadoEm(aditivo.getCriadoEm());

        // Mapeia dados básicos do contrato original
        ContratoSimplesDTO contratoDto = new ContratoSimplesDTO();
        contratoDto.setIdContrato(aditivo.getContrato().getIdContrato());
        contratoDto.setNumeroContrato(aditivo.getContrato().getNumeroContrato());
        contratoDto.setDataInicio(aditivo.getContrato().getDataInicio());
        contratoDto.setDescricao(aditivo.getContrato().getDescricao());
        // Ajuste abaixo se você tiver campos “parteContratante” e “parteContratada” no contrato
        contratoDto.setParteContratante(aditivo.getContrato().getEmpresa().getNomeFantasia());
        contratoDto.setParteContratada(aditivo.getContrato().getResponsavel().getNome());
        dto.setContrato(contratoDto);

        // Mapeia lista de documentos
        dto.setDocumentos(
                aditivo.getDocumentos().stream()
                        .map(AditivoDocumentoMapper::toDTO)
                        .collect(Collectors.toList())
        );

        return dto;
    }
}