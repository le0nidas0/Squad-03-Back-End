package com.example.squad03.service.impl;

import com.example.squad03.dto.DocumentoCreateDTO;
import com.example.squad03.dto.DocumentoResponseDTO;
import com.example.squad03.exception.RecursoNaoEncontradoException;
import com.example.squad03.mapper.DocumentoMapper;
import com.example.squad03.model.Contrato;
import com.example.squad03.model.Documento;
import com.example.squad03.repository.ContratoRepository;
import com.example.squad03.repository.DocumentoRepository;
import com.example.squad03.service.DocumentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DocumentoServiceImpl implements DocumentoService {

    private final DocumentoRepository documentoRepo;
    private final ContratoRepository contratoRepo;

    @Override
    @Transactional
    public DocumentoResponseDTO criarDocumento(DocumentoCreateDTO dto) {
        Contrato contrato = contratoRepo.findById(dto.getContratoId())
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Contrato não encontrado com ID " + dto.getContratoId()
                ));

        Documento doc = DocumentoMapper.toEntity(dto, contrato);
        doc = documentoRepo.save(doc);
        return DocumentoMapper.toDTO(doc);
    }

    @Override
    public DocumentoResponseDTO buscarPorId(Long id) {
        Documento doc = documentoRepo.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Documento não encontrado com ID " + id));
        return DocumentoMapper.toDTO(doc);
    }

    @Override
    public List<DocumentoResponseDTO> listarTodos() {
        return documentoRepo.findAll().stream()
                .map(DocumentoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<DocumentoResponseDTO> listarPorContrato(Long contratoId) {
        return documentoRepo.findByContrato_IdContrato(contratoId).stream()
                .map(DocumentoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public DocumentoResponseDTO atualizar(Long id, DocumentoCreateDTO dto) {
        Documento existente = documentoRepo.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Documento não encontrado com ID " + id));

        Contrato contrato = contratoRepo.findById(dto.getContratoId())
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Contrato não encontrado com ID " + dto.getContratoId()
                ));

        existente.setNomeArquivo(dto.getNomeArquivo());
        existente.setUrl(dto.getUrl());
        existente.setTipo(dto.getTipo());
        existente.setTamanho(dto.getTamanho());
        existente.setContrato(contrato);

        existente = documentoRepo.save(existente);
        return DocumentoMapper.toDTO(existente);
    }

    @Override
    @Transactional
    public void deletar(Long id) {
        Documento doc = documentoRepo.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Documento não encontrado com ID " + id));
        documentoRepo.delete(doc);
    }
}
