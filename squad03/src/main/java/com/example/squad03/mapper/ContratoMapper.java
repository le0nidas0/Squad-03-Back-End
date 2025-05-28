package com.example.squad03.mapper;

import com.example.squad03.dto.ContratoCreateDTO;
import com.example.squad03.dto.ContratoResponseDTO;
import com.example.squad03.model.Colaborador;
import com.example.squad03.model.Contrato;
import com.example.squad03.model.Empresa;
import com.example.squad03.model.Representante;

import java.time.LocalDateTime;

public class ContratoMapper {

    public static Contrato toEntity(
            ContratoCreateDTO dto,
            Empresa empresa,
            Colaborador responsavel,
            Representante representante
    ) {
        Contrato c = new Contrato();

        c.setNumeroContrato(dto.getNumeroContrato());
        c.setDescricao(dto.getDescricao());
        c.setDataInicio(dto.getDataInicio());
        c.setDataFim(dto.getDataFim());
        c.setTermosDePagamento(dto.getTermosDePagamento());
        c.setValorContrato(dto.getValorContrato());
        // totalPaid e remainingBalance são calculados/inicializados em service, não aqui
        c.setAutoRenovacao(dto.getAutoRenovacao() != null ? dto.getAutoRenovacao() : Boolean.FALSE);
        c.setDiasParaCancelamento(dto.getDiasParaCancelamento());
        c.setMotivoCancelamento(dto.getMotivoCancelamento());
        // criadoEm será preenchido pelo JPA auditing (@CreatedDate)
        // atualizadoEm idem (@LastModifiedDate)
        c.setStatusContrato(dto.getStatusContrato());
        c.setTipoContrato(dto.getTipoContrato());
        c.setTags(dto.getTags());
//        c.setDocumentUrl(dto.getDocumentUrl());

        c.setEmpresa(empresa);
        c.setResponsavel(responsavel);
        c.setRepresentante(representante);

        return c;
    }

    public static ContratoResponseDTO toDTO(Contrato c) {
        ContratoResponseDTO dto = new ContratoResponseDTO();

        dto.setIdContrato(c.getIdContrato());
        dto.setNumeroContrato(c.getNumeroContrato());
        dto.setDescricao(c.getDescricao());
        dto.setDataInicio(c.getDataInicio());
        dto.setDataFim(c.getDataFim());
        dto.setTermosDePagamento(c.getTermosDePagamento());
        dto.setValorContrato(c.getValorContrato());
        dto.setValorTotalPago(c.getValorTotalPago());
        dto.setValorTotalPendente(c.getValorTotalPendente());
        dto.setAutoRenovacao(c.getAutoRenovacao());
        dto.setDiasParaCancelamento(c.getDiasParaCancelamento());
        dto.setMotivoCancelamento(c.getMotivoCancelamento());
        dto.setCriadoEm(c.getCriadoEm());
        dto.setAtualizadoEm(c.getAtualizadoEm());
        dto.setCriadoPor(c.getCriadoPor());
        dto.setAtualizadoPor(c.getAtualizadoPor());
        dto.setStatusContrato(c.getStatusContrato());
        dto.setTipoContrato(c.getTipoContrato());
        dto.setTags(c.getTags());
//        dto.setDocumentUrl(c.getDocumentUrl());

        dto.setEmpresa(EmpresaMapper.toDTO(c.getEmpresa()));
        dto.setResponsavel(ColaboradorMapper.toDTO(c.getResponsavel()));
        dto.setRepresentante(RepresentanteMapper.toDTO(c.getRepresentante()));

        return dto;
    }
}
