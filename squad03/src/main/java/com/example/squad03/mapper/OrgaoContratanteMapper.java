package com.example.squad03.mapper;

import com.example.squad03.dto.OrgaoContratanteCreateDTO;
import com.example.squad03.dto.OrgaoContratanteResponseDTO;
import com.example.squad03.model.OrgaoContratante;

public class OrgaoContratanteMapper {

    public static OrgaoContratanteResponseDTO toDTO(OrgaoContratante entity) {
        OrgaoContratanteResponseDTO dto = new OrgaoContratanteResponseDTO();
        dto.setTipoEmpresa(entity.getTipoEmpresa());
        dto.setIdOrgao(entity.getIdOrgao());
        dto.setNome(entity.getNome());
        dto.setNomeFantasia(entity.getNomeFantasia());
        dto.setRazaoSocial(entity.getRazaoSocial());
        dto.setCnpj(entity.getCnpj());
        dto.setNumeroEmpresa(entity.getNumeroEmpresa());
        dto.setEstado(entity.getEstado());
        dto.setCidade(entity.getCidade());
        dto.setCep(entity.getCep());
        dto.setBairro(entity.getBairro());
        dto.setLogradouro(entity.getLogradouro());
        dto.setComplemento(entity.getComplemento());
        dto.setEmail(entity.getEmail());
        dto.setTelefone(entity.getTelefone());
        dto.setInscricaoMunicipal(entity.getInscricaoMunicipal());
        return dto;
    }

    public static OrgaoContratante toEntity(OrgaoContratanteCreateDTO dto) {
        OrgaoContratante entity = new OrgaoContratante();
        entity.setTipoEmpresa(dto.getTipoEmpresa());
        entity.setNome(dto.getNome());
        entity.setCnpj(dto.getCnpj());
        entity.setNomeFantasia(dto.getNomeFantasia());
        entity.setRazaoSocial(dto.getRazaoSocial());
        entity.setCep(dto.getCep());
        entity.setBairro(dto.getBairro());
        entity.setLogradouro(dto.getLogradouro());
        entity.setNumeroEmpresa(dto.getNumeroEmpresa());
        entity.setComplemento(dto.getComplemento());
        entity.setEstado(dto.getEstado());
        entity.setCidade(dto.getCidade());
        entity.setInscricaoMunicipal(dto.getInscricaoMunicipal());
        entity.setTelefone(dto.getTelefone());
        entity.setEmail(dto.getEmail());
        return entity;
    }
}