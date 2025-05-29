package com.example.squad03.service;

import com.example.squad03.dto.DocumentoCreateDTO;
import com.example.squad03.dto.DocumentoResponseDTO;

import java.util.List;

public interface DocumentoService {
    DocumentoResponseDTO criarDocumento(DocumentoCreateDTO dto);
    DocumentoResponseDTO buscarPorId(Long id);
    List<DocumentoResponseDTO> listarTodos();
    List<DocumentoResponseDTO> listarPorContrato(Long contratoId);
    DocumentoResponseDTO atualizar(Long id, DocumentoCreateDTO dto);
    void deletar(Long id);
}
