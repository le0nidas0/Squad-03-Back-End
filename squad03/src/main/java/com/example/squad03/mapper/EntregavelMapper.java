package com.example.squad03.mapper;

import com.example.squad03.dto.EntregavelCreateDTO;
import com.example.squad03.dto.EntregavelResponseDTO;
import com.example.squad03.model.Contrato;
import com.example.squad03.model.Entregavel;

import java.time.LocalDate;

public class EntregavelMapper {

    public static Entregavel toEntity(EntregavelCreateDTO dto, Contrato contrato) {
        Entregavel e = new Entregavel();
        e.setDescricao(dto.getDescricao());
        e.setPrazoEntrega(LocalDate.parse(dto.getPrazoEntrega()));
        e.setStatus(dto.getStatus());
        e.setContrato(contrato);
        return e;
    }

    public static EntregavelResponseDTO toDTO(Entregavel e) {
        EntregavelResponseDTO dto = new EntregavelResponseDTO();
        dto.setIdEntregavel(e.getIdEntregavel());
        dto.setDescricao(e.getDescricao());
        dto.setPrazoEntrega(e.getPrazoEntrega());
        dto.setStatus(e.getStatus());
        dto.setContratoId(e.getContrato().getIdContrato());
        return dto;
    }
}
