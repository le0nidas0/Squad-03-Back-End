package com.example.squad03.service.impl;

import com.example.squad03.dto.ContratoCreateDTO;
import com.example.squad03.dto.ContratoResponseDTO;
import com.example.squad03.enums.StatusContrato;
import com.example.squad03.exception.RecursoNaoEncontradoException;
import com.example.squad03.mapper.ContratoMapper;
import com.example.squad03.model.Colaborador;
import com.example.squad03.model.Contrato;
import com.example.squad03.model.Empresa;
import com.example.squad03.model.Representante;
import com.example.squad03.repository.ContratoRepository;
import com.example.squad03.repository.ColaboradorRepository;
import com.example.squad03.repository.EmpresaRepository;
import com.example.squad03.repository.RepresentanteRepository;
import com.example.squad03.service.ContratoService;
import com.example.squad03.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContratoServiceImpl implements ContratoService {

    private final ContratoRepository contratoRepository;
    private final ColaboradorRepository colaboradorRepository;
    private final EmpresaRepository empresaRepository;
    private final RepresentanteRepository representanteRepository;
    private final EmailService emailService;

    @Override
    @Transactional
    public ContratoResponseDTO criarContrato(ContratoCreateDTO dto) {
        Colaborador responsavel = colaboradorRepository.findById(dto.getResponsavelId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Responsável não encontrado com ID " + dto.getResponsavelId()));

        Empresa empresa = empresaRepository.findById(dto.getEmpresaId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Empresa não encontrada com ID " + dto.getEmpresaId()));

        Representante representante = representanteRepository.findById(dto.getRepresentanteId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Representante não encontrado com ID " + dto.getRepresentanteId()));

        // Mapeia DTO → entidade (sem valores pagos)
        Contrato contrato = ContratoMapper.toEntity(dto, empresa, responsavel, representante);

        // inicializa valores financeiros
        contrato.setValorTotalPago(BigDecimal.ZERO);
        contrato.setValorTotalPendente(dto.getValorContrato());

        // salva em banco
        contrato = contratoRepository.save(contrato);

        // notifica, se houver email
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
    public ContratoResponseDTO atualizar(Long id, ContratoCreateDTO dto) {
        Contrato existente = contratoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Contrato não encontrado com ID " + id));

        Colaborador responsavel = colaboradorRepository.findById(dto.getResponsavelId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Responsável não encontrado com ID " + dto.getResponsavelId()));

        Empresa empresa = empresaRepository.findById(dto.getEmpresaId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Empresa não encontrada com ID " + dto.getEmpresaId()));

        Representante representante = representanteRepository.findById(dto.getRepresentanteId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Representante não encontrado com ID " + dto.getRepresentanteId()));

        // Atualiza campos básicos
        existente.setNumeroContrato(dto.getNumeroContrato());
        existente.setDescricao(dto.getDescricao());
        existente.setDataInicio(dto.getDataInicio());
        existente.setDataFim(dto.getDataFim());
        existente.setTermosDePagamento(dto.getTermosDePagamento());
        existente.setValorContrato(dto.getValorContrato());
        existente.setAutoRenovacao(dto.getAutoRenovacao());
        existente.setDiasParaCancelamento(dto.getDiasParaCancelamento());
        existente.setMotivoCancelamento(dto.getMotivoCancelamento());
        existente.setStatusContrato(dto.getStatusContrato());
        existente.setTipoContrato(dto.getTipoContrato());
        existente.setTags(dto.getTags());
//        existente.setDocumentUrl(dto.getDocumentUrl());

        existente.setEmpresa(empresa);
        existente.setResponsavel(responsavel);
        existente.setRepresentante(representante);

        // Se o valorContrato mudou, ajuste o pendente (não zera o pago)
        existente.setValorTotalPendente(
                existente.getValorContrato()
                        .subtract(existente.getValorTotalPago())
        );

        // Salva
        existente = contratoRepository.save(existente);

        return ContratoMapper.toDTO(existente);
    }

    @Override
    @Transactional
    public void deletar(Long id) {
        Contrato contrato = contratoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Contrato não encontrado com ID " + id));
        contratoRepository.delete(contrato);
    }

    @Override
    @Transactional
    public void arquivarContrato(Long id) {
        Contrato contrato = contratoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Contrato não encontrado com ID " + id));

        contrato.setStatusContrato(StatusContrato.ARQUIVADO);
        contratoRepository.save(contrato);
    }

    @Override
    public List<ContratoResponseDTO> listarContratosArquivados() {
        return contratoRepository.findByStatusContrato(StatusContrato.ARQUIVADO)
                .stream()
                .map(ContratoMapper::toDTO)
                .collect(Collectors.toList());
    }
}

