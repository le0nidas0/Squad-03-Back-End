package com.example.squad03.mapper;

import com.example.squad03.dto.OrgaoContratanteCreateDTO;
import com.example.squad03.dto.OrgaoContratanteResponseDTO;
import com.example.squad03.model.OrgaoContratante;

public class OrgaoContratanteMapper {

    public static OrgaoContratanteResponseDTO toDTO(OrgaoContratante entity) {
        OrgaoContratanteResponseDTO dto = new OrgaoContratanteResponseDTO();
        dto.setIdOrgao(entity.getIdOrgao());
        dto.setNome(entity.getNome());
        dto.setNomeFantasia(entity.getNomeFantasia());
        dto.setRazaoSocial(entity.getRazaoSocial());
        dto.setNumeroEmpresa(entity.getNumeroEmpresa());
        dto.setEstado(entity.getEstado());
        dto.setCidade(entity.getCidade());
        return dto;
    }

    public static OrgaoContratante toEntity(OrgaoContratanteCreateDTO dto) {
        OrgaoContratante entity = new OrgaoContratante();
        entity.setNome(dto.getNome());
        entity.setCnpj(dto.getCnpj());
        entity.setNomeFantasia(dto.getNomeFantasia());
        entity.setRazaoSocial(dto.getRazaoSocial());
        entity.setNumeroEmpresa(dto.getNumeroEmpresa());
        entity.setEstado(dto.getEstado());
        entity.setCidade(dto.getCidade());
        return entity;
    }
}