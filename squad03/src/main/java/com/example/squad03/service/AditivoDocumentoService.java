package com.example.squad03.service;

import com.example.squad03.dto.AditivoDocumentoCreateDTO;
import com.example.squad03.dto.AditivoDocumentoResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AditivoDocumentoService {
    AditivoDocumentoResponseDTO anexarDocumento(AditivoDocumentoCreateDTO dto);

    byte[] baixarDocumento(Long idDocumento);

    List<AditivoDocumentoResponseDTO> listarPorAditivo(Long aditivoId);

    void deletar(Long idDocumento);

    AditivoDocumentoResponseDTO buscarPorId(Long idDocumento);
}