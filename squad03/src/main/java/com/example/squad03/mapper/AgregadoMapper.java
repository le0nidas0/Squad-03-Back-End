package com.example.squad03.mapper;

import com.example.squad03.dto.*;
import com.example.squad03.model.Agregado;

public class AgregadoMapper {

    public static AgregadoResponseDTO toDTO(Agregado entity) {
        AgregadoResponseDTO dto = new AgregadoResponseDTO();

        dto.setIdAgregado(entity.getIdAgregado());
        dto.setFuncao(entity.getFuncao());
        dto.setDataInicio(entity.getDataInicio());
        dto.setDataFim(entity.getDataFim());
        dto.setCriadoEm(entity.getCriadoEm());

        // Mapeia o contrato associado via ContratoSimplesDTO
        ContratoSimplesDTO contratoDto = new ContratoSimplesDTO();
        contratoDto.setIdContrato(entity.getContrato().getIdContrato());
        contratoDto.setNumeroContrato(entity.getContrato().getNumeroContrato());
        contratoDto.setDataInicio(entity.getContrato().getDataInicio());
        contratoDto.setDescricao(entity.getContrato().getDescricao());
        contratoDto.setParteContratante(entity.getContrato().getEmpresa().getNomeFantasia());
        contratoDto.setParteContratada(entity.getContrato().getResponsavel().getNome());
        dto.setContrato(contratoDto);

        // Mapeia o colaborador associado via ColaboradorSimplesDTO
        ColaboradorSimplesDTO colabDto = new ColaboradorSimplesDTO();
        colabDto.setIdFuncionario(entity.getColaborador().getIdFuncionario());
        colabDto.setNome(entity.getColaborador().getNome());
        colabDto.setCpf(entity.getColaborador().getCpf());
        colabDto.setCargo(entity.getColaborador().getCargo());
        dto.setColaborador(colabDto);

        return dto;
    }
}
