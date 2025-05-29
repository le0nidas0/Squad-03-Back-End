package com.example.squad03.mapper;

import com.example.squad03.dto.DocumentoCreateDTO;
import com.example.squad03.dto.DocumentoResponseDTO;
import com.example.squad03.model.Contrato;
import com.example.squad03.model.Documento;

public class DocumentoMapper {

    public static Documento toEntity(DocumentoCreateDTO dto, Contrato contrato) {
        Documento doc = new Documento();
        doc.setNomeArquivo(dto.getNomeArquivo());
        doc.setUrl(dto.getUrl());
        doc.setTipo(dto.getTipo());
        doc.setTamanho(dto.getTamanho());
        doc.setContrato(contrato);
        return doc;
    }

    public static DocumentoResponseDTO toDTO(Documento doc) {
        DocumentoResponseDTO dto = new DocumentoResponseDTO();
        dto.setIdDocumento(doc.getIdDocumento());
        dto.setNomeArquivo(doc.getNomeArquivo());
        dto.setUrl(doc.getUrl());
        dto.setTipo(doc.getTipo());
        dto.setTamanho(doc.getTamanho());
        dto.setCriadoEm(doc.getCriadoEm());
        dto.setContrato(ContratoMapper.toDTO(doc.getContrato()));
        return dto;
    }
}
