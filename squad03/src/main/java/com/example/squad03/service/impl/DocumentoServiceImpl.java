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
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DocumentoServiceImpl implements DocumentoService {

    private final DocumentoRepository documentoRepo;
    private final ContratoRepository contratoRepo;

    @Override
    @Transactional
    public DocumentoResponseDTO anexarDocumento(Long contratoId, MultipartFile file) {
        // MIME types permitidos
        List<String> allowedContentTypes = List.of(
                "application/pdf",
                "image/png",
                "image/jpeg",
                "application/vnd.openxmlformats-officedocument.wordprocessingml.document" // .docx
        );
        // Extensões permitidas
        List<String> allowedExtensions = List.of("pdf", "png", "jpg", "jpeg", "docx");

        String contentType = file.getContentType();
        String originalName = file.getOriginalFilename();
        String extension = originalName != null
                ? StringUtils.getFilenameExtension(originalName).toLowerCase()
                : "";

        // valida MIME type
        if (!allowedContentTypes.contains(contentType)) {
            throw new IllegalArgumentException("Tipo de arquivo não suportado: " + contentType);
        }
        // valida extensão
        if (!allowedExtensions.contains(extension)) {
            throw new IllegalArgumentException("Extensão de arquivo não suportada: ." + extension);
        }

        Contrato contrato = contratoRepo.findById(contratoId)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException("Contrato não encontrado com ID " + contratoId)
                );

        try {
            Documento doc = new Documento();
            doc.setNomeArquivo(originalName);
            doc.setMimeType(contentType);
            doc.setTamanho(file.getSize());
            doc.setDados(file.getBytes());
            doc.setContrato(contrato);

            doc = documentoRepo.save(doc);
            return DocumentoMapper.toDTO(doc);

        } catch (IOException e) {
            throw new RuntimeException("Falha ao processar arquivo", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public DocumentoResponseDTO buscarPorId(Long id) {
        Documento doc = documentoRepo.findById(id)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException("Documento não encontrado com ID " + id)
                );
        return DocumentoMapper.toDTO(doc);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DocumentoResponseDTO> listarPorContrato(Long idContrato) {
        return documentoRepo.findAllByContrato_idContrato(idContrato).stream()
                .map(DocumentoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deletar(Long id) {
        Documento doc = documentoRepo.findById(id)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException("Documento não encontrado com ID " + id)
                );
        documentoRepo.delete(doc);
    }

    @Override
    @Transactional(readOnly = true)
    public byte[] buscarDados(Long idDocumento) {
        Documento doc = documentoRepo.findById(idDocumento)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException("Documento não encontrado com ID " + idDocumento)
                );
        return doc.getDados();
    }
}
