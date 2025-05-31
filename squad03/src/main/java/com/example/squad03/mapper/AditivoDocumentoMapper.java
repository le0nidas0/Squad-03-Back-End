package com.example.squad03.mapper;

import com.example.squad03.dto.AditivoDocumentoResponseDTO;
import com.example.squad03.model.AditivoDocumento;

public class AditivoDocumentoMapper {

    public static AditivoDocumentoResponseDTO toDTO(AditivoDocumento doc) {
        AditivoDocumentoResponseDTO dto = new AditivoDocumentoResponseDTO();
        dto.setIdDocumento(doc.getIdDocumento());
        dto.setNomeArquivo(doc.getNomeArquivo());
        dto.setMimeType(doc.getMimeType());
        dto.setTamanho(doc.getTamanho());
        dto.setCriadoEm(doc.getCriadoEm());
        return dto;
    }
}