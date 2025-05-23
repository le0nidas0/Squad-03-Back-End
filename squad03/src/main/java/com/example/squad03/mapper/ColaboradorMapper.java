package com.example.squad03.mapper;

import com.example.squad03.dto.ColaboradorCreateDTO;
import com.example.squad03.dto.ColaboradorResponseDTO;
import com.example.squad03.model.Colaborador;

public class ColaboradorMapper {

    public static ColaboradorResponseDTO toDTO(Colaborador entity) {
        ColaboradorResponseDTO dto = new ColaboradorResponseDTO();
        dto.setIdFuncionario(entity.getIdFuncionario());
        dto.setNome(entity.getNome());
        dto.setCargo(entity.getCargo());
        dto.setTelefone(entity.getTelefone());
        dto.setEmail(entity.getEmail());
        dto.setCpf(entity.getCpf());
        dto.setDataNascimento(entity.getDataNascimento());
        return dto;
    }

    public static Colaborador toEntity(ColaboradorCreateDTO dto) {
        Colaborador entity = new Colaborador();
        entity.setNome(dto.getNome());
        entity.setEmail(dto.getEmail());
        entity.setCpf(dto.getCpf());
        entity.setCargo(dto.getCargo());
        entity.setTelefone(dto.getTelefone());
        entity.setDataNascimento(dto.getDataNascimento());
        return entity;
    }
}
