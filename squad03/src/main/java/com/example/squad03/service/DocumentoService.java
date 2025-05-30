package com.example.squad03.service;

import com.example.squad03.dto.DocumentoCreateDTO;
import com.example.squad03.dto.DocumentoResponseDTO;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface DocumentoService {
    DocumentoResponseDTO anexarDocumento(Long contratoId, MultipartFile file);
    DocumentoResponseDTO buscarPorId(Long id);
    List<DocumentoResponseDTO> listarPorContrato(Long contratoId);
    void deletar(Long id);
    byte[] buscarDados(Long idDocumento);
}