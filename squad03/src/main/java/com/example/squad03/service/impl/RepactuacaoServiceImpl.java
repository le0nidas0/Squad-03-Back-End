package com.example.squad03.service.impl;

import com.example.squad03.dto.RepactuacaoCreateDTO;
import com.example.squad03.dto.RepactuacaoResponseDTO;
import com.example.squad03.exception.RecursoNaoEncontradoException;
import com.example.squad03.mapper.RepactuacaoMapper;
import com.example.squad03.model.Contrato;
import com.example.squad03.model.Repactuacao;
import com.example.squad03.repository.ContratoRepository;
import com.example.squad03.repository.RepactuacaoRepository;
import com.example.squad03.service.RepactuacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RepactuacaoServiceImpl implements RepactuacaoService {

    private final RepactuacaoRepository repactuacaoRepo;
    private final ContratoRepository contratoRepo;

    @Override
    @Transactional
    public RepactuacaoResponseDTO criarRepactuacao(RepactuacaoCreateDTO dto) {
        Contrato contrato = contratoRepo.findById(dto.getContratoId())
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException("Contrato não encontrado com ID " + dto.getContratoId())
                );

        Repactuacao r = RepactuacaoMapper.toEntity(dto, contrato);
        r = repactuacaoRepo.save(r);

        return RepactuacaoMapper.toDTO(r);
    }

    @Override
    @Transactional(readOnly = true)
    public RepactuacaoResponseDTO buscarPorId(Long id) {
        Repactuacao r = repactuacaoRepo.findById(id)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException("Repactuação não encontrada com ID " + id)
                );
        return RepactuacaoMapper.toDTO(r);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RepactuacaoResponseDTO> listarPorContrato(Long idContrato) {
        return repactuacaoRepo.findByContrato_idContrato(idContrato).stream()
                .map(RepactuacaoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public RepactuacaoResponseDTO atualizar(Long id, RepactuacaoCreateDTO dto) {
        Repactuacao existente = repactuacaoRepo.findById(id)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException("Repactuação não encontrada com ID " + id)
                );

        Contrato contrato = contratoRepo.findById(dto.getContratoId())
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException("Contrato não encontrado com ID " + dto.getContratoId())
                );

        existente.setIndice(dto.getIndice());
        existente.setDataBase(dto.getDataBase());
        existente.setValorAnterior(dto.getValorAnterior());
        existente.setValorRepactuado(dto.getValorRepactuado());
        existente.setJustificativa(dto.getJustificativa());
        existente.setContrato(contrato);

        existente = repactuacaoRepo.save(existente);
        return RepactuacaoMapper.toDTO(existente);
    }

    @Override
    @Transactional
    public void deletar(Long id) {
        Repactuacao r = repactuacaoRepo.findById(id)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException("Repactuação não encontrada com ID " + id)
                );
        repactuacaoRepo.delete(r);
    }
}
