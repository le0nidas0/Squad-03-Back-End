package com.example.squad03.mapper;

import com.example.squad03.dto.EntregavelCreateDTO;
import com.example.squad03.dto.EntregavelResponseDTO;
import com.example.squad03.model.Colaborador;
import com.example.squad03.model.Contrato;
import com.example.squad03.model.Entregavel;

import java.time.LocalDate;

public class EntregavelMapper {

    public static Entregavel toEntity(EntregavelCreateDTO dto, Contrato contrato, Colaborador colaborador) {
        Entregavel e = new Entregavel();
        e.setTitulo(dto.getTitulo());
        e.setDescricao(dto.getDescricao());
        e.setPrazoEntrega(LocalDate.parse(dto.getPrazoEntrega()));
        e.setStatus(dto.getStatus());
        e.setResponsavel(colaborador);
        e.setContrato(contrato);
        return e;
    }

    public static EntregavelResponseDTO toDTO(Entregavel e) {
        EntregavelResponseDTO dto = new EntregavelResponseDTO();
        dto.setIdEntregavel(e.getIdEntregavel());
        dto.setTitulo(e.getTitulo());
        dto.setDescricao(e.getDescricao());
        dto.setPrazoEntrega(e.getPrazoEntrega());
        dto.setStatus(e.getStatus());
        dto.setResponsavelId(e.getResponsavel().getIdFuncionario());
        dto.setContratoId(e.getContrato().getIdContrato());
        return dto;
    }
}
