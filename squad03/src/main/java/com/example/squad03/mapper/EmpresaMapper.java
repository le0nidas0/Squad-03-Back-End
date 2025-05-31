package com.example.squad03.mapper;

import com.example.squad03.dto.EmpresaCreateDTO;
import com.example.squad03.dto.EmpresaResponseDTO;
import com.example.squad03.model.Empresa;

import java.util.stream.Collectors;

public class EmpresaMapper {

    public static EmpresaResponseDTO toDTO(Empresa entity) {
        EmpresaResponseDTO dto = new EmpresaResponseDTO();
        dto.setTipoEmpresa(entity.getTipoEmpresa());
        dto.setIdOrgao(entity.getIdOrgao());
        dto.setNomeFantasia(entity.getNomeFantasia());
        dto.setRazaoSocial(entity.getRazaoSocial());
        dto.setCnpj(entity.getCnpj());
//        dto.setNumeroEmpresa(entity.getNumeroEmpresa());
        dto.setEstado(entity.getEstado());
        dto.setCidade(entity.getCidade());
        dto.setCep(entity.getCep());
        dto.setBairro(entity.getBairro());
        dto.setLogradouro(entity.getLogradouro());
        dto.setNumero(entity.getNumero());
        dto.setComplemento(entity.getComplemento());
        dto.setEmail(entity.getEmail());
        dto.setTelefone(entity.getTelefone());
        dto.setInscricaoMunicipal(entity.getInscricaoMunicipal());
        dto.setRepresentantes(
                entity.getRepresentantes() != null
                        ? entity.getRepresentantes().stream()
                        .map(RepresentanteMapper::toDTO)
                        .collect(Collectors.toList())
                        : null
        );
        return dto;
    }

    public static Empresa toEntity(EmpresaCreateDTO dto) {
        Empresa entity = new Empresa();
        entity.setTipoEmpresa(dto.getTipoEmpresa());
        entity.setCnpj(dto.getCnpj());
        entity.setNomeFantasia(dto.getNomeFantasia());
        entity.setRazaoSocial(dto.getRazaoSocial());
        entity.setCep(dto.getCep());
        entity.setBairro(dto.getBairro());
        entity.setLogradouro(dto.getLogradouro());
        entity.setNumero(dto.getNumero());
//        entity.setNumeroEmpresa(dto.getNumeroEmpresa());
        entity.setComplemento(dto.getComplemento());
        entity.setEstado(dto.getEstado());
        entity.setCidade(dto.getCidade());
        entity.setInscricaoMunicipal(dto.getInscricaoMunicipal());
        entity.setTelefone(dto.getTelefone());
        entity.setEmail(dto.getEmail());
        return entity;
    }
}