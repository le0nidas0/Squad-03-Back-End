package com.example.squad03.mapper;

import com.example.squad03.dto.DocumentoResponseDTO;
import com.example.squad03.model.Documento;

public class DocumentoMapper {

    public static DocumentoResponseDTO toDTO(Documento doc) {
        DocumentoResponseDTO dto = new DocumentoResponseDTO();
        dto.setIdDocumento(doc.getIdDocumento());
        dto.setNomeArquivo(doc.getNomeArquivo());
        dto.setMimeType(doc.getMimeType());
        dto.setTamanho(doc.getTamanho());
        dto.setCriadoEm(doc.getCriadoEm());
        dto.setContratoId(doc.getContrato().getIdContrato());
        return dto;
    }
}