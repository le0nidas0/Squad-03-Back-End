package com.example.squad03.mapper;

import com.example.squad03.dto.FuncionarioCreateDTO;
import com.example.squad03.dto.FuncionarioResponseDTO;
import com.example.squad03.model.Funcionario;

public class FuncionarioMapper {

    public static FuncionarioResponseDTO toDTO(Funcionario entity) {
        FuncionarioResponseDTO dto = new FuncionarioResponseDTO();
        dto.setIdFuncionario(entity.getIdFuncionario());
        dto.setNome(entity.getNome());
        dto.setCargo(entity.getCargo());
        return dto;
    }

    public static Funcionario toEntity(FuncionarioCreateDTO dto) {
        Funcionario entity = new Funcionario();
        entity.setNome(dto.getNome());
        entity.setEmail(dto.getEmail());
        entity.setCpf(dto.getCpf());
        entity.setCargo(dto.getCargo());
        entity.setTelefone(dto.getTelefone());
        return entity;
    }
}
