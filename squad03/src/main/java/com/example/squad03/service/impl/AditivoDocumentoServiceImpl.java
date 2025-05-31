package com.example.squad03.service.impl;

import com.example.squad03.dto.AditivoDocumentoCreateDTO;
import com.example.squad03.dto.AditivoDocumentoResponseDTO;
import com.example.squad03.exception.RecursoNaoEncontradoException;
import com.example.squad03.mapper.AditivoDocumentoMapper;
import com.example.squad03.model.AditivoContractual;
import com.example.squad03.model.AditivoDocumento;
import com.example.squad03.repository.AditivoContractualRepository;
import com.example.squad03.repository.AditivoDocumentoRepository;
import com.example.squad03.service.AditivoDocumentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AditivoDocumentoServiceImpl implements AditivoDocumentoService {

    private final AditivoDocumentoRepository docRepo;
    private final AditivoContractualRepository aditivoRepo;

    @Override
    @Transactional
    public AditivoDocumentoResponseDTO anexarDocumento(AditivoDocumentoCreateDTO dto) {
        MultipartFile file = dto.getFile();
        String contentType = file.getContentType();
        String filename = file.getOriginalFilename();
        String extension = (filename != null)
                ? StringUtils.getFilenameExtension(filename).toLowerCase()
                : "";

        List<String> allowedTypes = List.of(
                "application/pdf",
                "image/png",
                "image/jpeg",
                "application/vnd.openxmlformats-officedocument.wordprocessingml.document"
        );
        List<String> allowedExts = List.of("pdf","png","jpg","jpeg","docx");

        if (!allowedTypes.contains(contentType) || !allowedExts.contains(extension)) {
            throw new IllegalArgumentException("Tipo de arquivo não suportado");
        }

        AditivoContractual aditivo = aditivoRepo.findById(dto.getAditivoId())
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException("Aditivo não encontrado com ID " + dto.getAditivoId())
                );

        try {
            AditivoDocumento doc = new AditivoDocumento();
            doc.setNomeArquivo(filename);
            doc.setMimeType(contentType);
            doc.setTamanho(file.getSize());
            doc.setDados(file.getBytes());
            doc.setAditivo(aditivo);

            doc = docRepo.save(doc);
            return AditivoDocumentoMapper.toDTO(doc);
        } catch (IOException e) {
            throw new RuntimeException("Falha ao processar arquivo", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public byte[] baixarDocumento(Long idDocumento) {
        AditivoDocumento doc = docRepo.findById(idDocumento)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException("Documento não encontrado com ID " + idDocumento)
                );
        return doc.getDados();
    }

    @Override
    @Transactional(readOnly = true)
    public List<AditivoDocumentoResponseDTO> listarPorAditivo(Long aditivoId) {
        return docRepo.findByAditivoIdAditivoContractual(aditivoId).stream()
                .map(AditivoDocumentoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deletar(Long idDocumento) {
        AditivoDocumento doc = docRepo.findById(idDocumento)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException("Documento não encontrado com ID " + idDocumento)
                );
        docRepo.delete(doc);
    }

    @Override
    @Transactional(readOnly = true)
    public AditivoDocumentoResponseDTO buscarPorId(Long idDocumento) {
        AditivoDocumento doc = docRepo.findById(idDocumento)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException("Documento não encontrado com ID " + idDocumento)
                );
        return AditivoDocumentoMapper.toDTO(doc);
    }
}