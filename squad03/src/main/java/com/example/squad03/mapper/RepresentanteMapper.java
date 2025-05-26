package com.example.squad03.mapper;

import com.example.squad03.dto.RepresentanteCreateDTO;
import com.example.squad03.dto.RepresentanteResponseDTO;
import com.example.squad03.model.Empresa;
import com.example.squad03.model.Representante;

public class RepresentanteMapper {

    public static Representante toEntity(RepresentanteCreateDTO dto, Empresa orgao) {
        Representante representante = new Representante();
        representante.setNome(dto.getNome());
        representante.setCpf(dto.getCpf());
        representante.setEmail(dto.getEmail());
        representante.setTelefone(dto.getTelefone());
        representante.setEmpresa(orgao);
        return representante;
    }

    public static RepresentanteResponseDTO toDTO(Representante entity) {
        RepresentanteResponseDTO dto = new RepresentanteResponseDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setEmail(entity.getEmail());
        dto.setCpf(entity.getCpf());
        dto.setTelefone(entity.getTelefone());
        return dto;
    }

}
