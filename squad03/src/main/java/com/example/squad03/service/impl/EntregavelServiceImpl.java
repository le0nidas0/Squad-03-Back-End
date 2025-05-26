package com.example.squad03.service.impl;

import com.example.squad03.dto.EntregavelCreateDTO;
import com.example.squad03.dto.EntregavelResponseDTO;
import com.example.squad03.exception.RecursoNaoEncontradoException;
import com.example.squad03.mapper.EntregavelMapper;
import com.example.squad03.model.Contrato;
import com.example.squad03.model.Entregavel;
import com.example.squad03.repository.ContratoRepository;
import com.example.squad03.repository.EntregavelRepository;
import com.example.squad03.service.EntregavelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EntregavelServiceImpl implements EntregavelService {

    private final EntregavelRepository entregavelRepository;
    private final ContratoRepository contratoRepository;

    @Override
    @Transactional
    public EntregavelResponseDTO criarEntregavel(EntregavelCreateDTO dto) {
        Contrato contrato = contratoRepository.findById(dto.getContratoId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Contrato não encontrado com ID " + dto.getContratoId()));

        Entregavel e = EntregavelMapper.toEntity(dto, contrato);
        e = entregavelRepository.save(e);
        return EntregavelMapper.toDTO(e);
    }

    @Override
    public List<EntregavelResponseDTO> listarTodos() {
        return entregavelRepository.findAll()
                .stream()
                .map(EntregavelMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EntregavelResponseDTO buscarPorId(Long id) {
        Entregavel e = entregavelRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Entregável não encontrado com ID " + id));
        return EntregavelMapper.toDTO(e);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EntregavelResponseDTO> buscarPorContratoId(Long contratoId) {
        // verifica se o contrato existe
        contratoRepository.findById(contratoId)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Contrato não encontrado com ID " + contratoId));

        // busca os entregáveis vinculados
        return entregavelRepository.findAllByContrato_idContrato(contratoId).stream()
                .map(EntregavelMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public EntregavelResponseDTO atualizar(Long id, EntregavelCreateDTO dto) {
        Entregavel existente = entregavelRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Entregável não encontrado com ID " + id));

        existente.setDescricao(dto.getDescricao());
        existente.setPrazoEntrega(LocalDate.parse(dto.getPrazoEntrega()));
        existente.setStatus(dto.getStatus());
        // contrato não é alterado aqui; se precisar, buscar e setar de forma similar

        existente = entregavelRepository.save(existente);
        return EntregavelMapper.toDTO(existente);
    }

    @Override
    @Transactional
    public void deletar(Long id) {
        Entregavel e = entregavelRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Entregável não encontrado com ID " + id));
        entregavelRepository.delete(e);
    }
}
