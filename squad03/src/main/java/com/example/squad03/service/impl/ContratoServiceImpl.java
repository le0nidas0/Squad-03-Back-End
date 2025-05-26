package com.example.squad03.service.impl;

import com.example.squad03.dto.ContratoCreateDTO;
import com.example.squad03.dto.ContratoResponseDTO;
import com.example.squad03.enums.StatusContrato;
import com.example.squad03.exception.RecursoNaoEncontradoException;
import com.example.squad03.mapper.ContratoMapper;
import com.example.squad03.model.Colaborador;
import com.example.squad03.model.Contrato;
import com.example.squad03.model.Empresa;
import com.example.squad03.repository.ContratoRepository;
import com.example.squad03.repository.ColaboradorRepository;
import com.example.squad03.repository.EmpresaRepository;
import com.example.squad03.service.ContratoService;
import com.example.squad03.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContratoServiceImpl implements ContratoService {

    private final ContratoRepository contratoRepository;
    private final ColaboradorRepository colaboradorRepository;
    private final EmpresaRepository orgaoRepository;
    private final EmailService emailService;

    @Override
    public ContratoResponseDTO criarContrato(ContratoCreateDTO dto) {
        Colaborador responsavel = colaboradorRepository.findById(dto.getResponsavelId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Responsável não encontrado."));

        Empresa orgao = orgaoRepository.findById(dto.getEmpresaId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Órgão contratante não encontrado."));

        Contrato contrato = ContratoMapper.toEntity(dto, orgao, responsavel);
        contrato = contratoRepository.save(contrato);

        if (responsavel.getEmail() != null && !responsavel.getEmail().isBlank()) {
            emailService.enviarNotificacaoResponsavelContrato(
                    responsavel.getEmail(),
                    responsavel.getNome(),
                    contrato.getIdContrato()
            );
        }

        return ContratoMapper.toDTO(contrato);
    }


    @Override
    public ContratoResponseDTO buscarPorId(Long id) {
        return contratoRepository.findById(id)
                .map(ContratoMapper::toDTO)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Contrato não encontrado com ID " + id));
    }

    @Override
    public List<ContratoResponseDTO> listarTodos() {
        return contratoRepository.findAll()
                .stream()
                .map(ContratoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ContratoResponseDTO> listarContratosArquivados() {
        List<Contrato> contratosArquivados = contratoRepository.findByStatus(StatusContrato.ARQUIVADO);
        return contratosArquivados.stream()
                .map(ContratoMapper::toDTO)
                .toList();
    }

    public void arquivarContrato(Long idContrato) {
        Contrato contrato = contratoRepository.findById(idContrato)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Contrato não encontrado com ID: " + idContrato));

        contrato.setStatus(StatusContrato.ARQUIVADO);
        contratoRepository.save(contrato);
    }

    @Override
    public ContratoResponseDTO atualizar(Long id, ContratoCreateDTO dto) {
        Contrato contratoExistente = contratoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Contrato não encontrado com ID " + id));

        Colaborador responsavel = colaboradorRepository.findById(dto.getResponsavelId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Responsável não encontrado."));

        Empresa orgao = orgaoRepository.findById(dto.getEmpresaId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Órgão contratante não encontrado."));

        contratoExistente.setPrazo(dto.getPrazo());
        contratoExistente.setStatus(dto.getStatus());
        contratoExistente.setValor(dto.getValor());
        contratoExistente.setResponsavel(responsavel);
        contratoExistente.setEmpresa(orgao);

        contratoExistente = contratoRepository.save(contratoExistente);

        return ContratoMapper.toDTO(contratoExistente);
    }

    public void verificarContratosProximosDoFim() {
        List<Contrato> contratos = contratoRepository.findAll();

        LocalDate hoje = LocalDate.now();

        for (Contrato contrato : contratos) {
            if (contrato.getPrazo() != null && !contrato.getStatus().equals(StatusContrato.ARQUIVADO)) {
                LocalDate prazo = contrato.getPrazo();
                if (prazo.minusDays(7).isEqual(hoje)) {
                    Colaborador responsavel = contrato.getResponsavel();
                    if (responsavel != null && responsavel.getEmail() != null) {
                        emailService.enviarAvisoContrato(
                                responsavel.getEmail(),
                                responsavel.getNome(),
                                "ID " + contrato.getIdContrato()
                        );
                    }
                }
            }
        }
    }
}

