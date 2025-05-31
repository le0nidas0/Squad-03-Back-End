package com.example.squad03.mapper;

import com.example.squad03.dto.ContratoSimplesDTO;
import com.example.squad03.dto.RepactuacaoCreateDTO;
import com.example.squad03.dto.RepactuacaoResponseDTO;
import com.example.squad03.model.Repactuacao;

public class RepactuacaoMapper {

    public static Repactuacao toEntity(RepactuacaoCreateDTO dto, com.example.squad03.model.Contrato contrato) {
        Repactuacao r = new Repactuacao();
        r.setIndice(dto.getIndice());
        r.setDataBase(dto.getDataBase());
        r.setValorAnterior(dto.getValorAnterior());
        r.setValorRepactuado(dto.getValorRepactuado());
        r.setJustificativa(dto.getJustificativa());
        r.setContrato(contrato);
        return r;
    }

    public static RepactuacaoResponseDTO toDTO(Repactuacao r) {
        RepactuacaoResponseDTO dto = new RepactuacaoResponseDTO();
        dto.setIdRepactuacao(r.getIdRepactuacao());
        dto.setIndice(r.getIndice());
        dto.setDataBase(r.getDataBase());
        dto.setValorAnterior(r.getValorAnterior());
        dto.setValorRepactuado(r.getValorRepactuado());
        dto.setJustificativa(r.getJustificativa());
        dto.setCriadoEm(r.getCriadoEm());

        // popula ContratoSimplesDTO
        ContratoSimplesDTO c = new ContratoSimplesDTO();
        c.setIdContrato(r.getContrato().getIdContrato());
        c.setNumeroContrato(r.getContrato().getNumeroContrato());
        c.setDataInicio(r.getContrato().getDataInicio());
        c.setDescricao(r.getContrato().getDescricao());
        c.setParteContratante(r.getContrato().getEmpresa().getNomeFantasia());
        c.setParteContratada(r.getContrato().getResponsavel().getNome());
        dto.setContrato(c);

        return dto;
    }
}
