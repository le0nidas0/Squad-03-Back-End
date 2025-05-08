package com.example.squad03.service.impl;

import com.example.squad03.dto.ContratoCreateDTO;
import com.example.squad03.dto.ContratoResponseDTO;
import com.example.squad03.enums.StatusContrato;
import com.example.squad03.exception.RecursoNaoEncontradoException;
import com.example.squad03.mapper.ContratoMapper;
import com.example.squad03.model.Contrato;
import com.example.squad03.model.Funcionario;
import com.example.squad03.model.OrgaoContratante;
import com.example.squad03.repository.ContratoRepository;
import com.example.squad03.repository.FuncionarioRepository;
import com.example.squad03.repository.OrgaoContratanteRepository;
import com.example.squad03.service.ContratoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContratoServiceImpl implements ContratoService {

    private final ContratoRepository contratoRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final OrgaoContratanteRepository orgaoRepository;

    @Override
    public ContratoResponseDTO criarContrato(ContratoCreateDTO dto) {
        Funcionario responsavel = funcionarioRepository.findById(dto.getResponsavelId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Responsável não encontrado."));

        OrgaoContratante orgao = orgaoRepository.findById(dto.getOrgaoContratanteId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Órgão contratante não encontrado."));

        Contrato contrato = ContratoMapper.toEntity(dto, orgao, responsavel);
        contrato = contratoRepository.save(contrato);

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

        Funcionario responsavel = funcionarioRepository.findById(dto.getResponsavelId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Responsável não encontrado."));

        OrgaoContratante orgao = orgaoRepository.findById(dto.getOrgaoContratanteId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Órgão contratante não encontrado."));

        contratoExistente.setPrazo(dto.getPrazo());
        contratoExistente.setStatus(dto.getStatus());
        contratoExistente.setValor(dto.getValor());
        contratoExistente.setResponsavel(responsavel);
        contratoExistente.setOrgaoContratante(orgao);

        contratoExistente = contratoRepository.save(contratoExistente);

        return ContratoMapper.toDTO(contratoExistente);
    }
}

