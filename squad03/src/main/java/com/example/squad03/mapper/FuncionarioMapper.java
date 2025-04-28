package com.example.squad03.mapper;

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
}
