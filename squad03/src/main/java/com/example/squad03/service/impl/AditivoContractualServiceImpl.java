package com.example.squad03.service.impl;

import com.example.squad03.dto.AditivoContractualCreateDTO;
import com.example.squad03.dto.AditivoContractualResponseDTO;
import com.example.squad03.exception.RecursoNaoEncontradoException;
import com.example.squad03.mapper.AditivoContractualMapper;
import com.example.squad03.model.AditivoContractual;
import com.example.squad03.model.Contrato;
import com.example.squad03.repository.AditivoContractualRepository;
import com.example.squad03.repository.ContratoRepository;
import com.example.squad03.service.AditivoContractualService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AditivoContractualServiceImpl implements AditivoContractualService {

    private final AditivoContractualRepository aditivoRepo;
    private final ContratoRepository contratoRepo;

    @Override
    @Transactional
    public AditivoContractualResponseDTO criarAditivo(AditivoContractualCreateDTO dto) {
        Contrato contrato = contratoRepo.findById(dto.getContratoId())
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException("Contrato não encontrado com ID " + dto.getContratoId())
                );

        AditivoContractual aditivo = new AditivoContractual();
        aditivo.setTipo(dto.getTipo());
        aditivo.setDescricaoMudancas(dto.getDescricaoMudancas());
        aditivo.setJustificativa(dto.getJustificativa());
        aditivo.setDataVigencia(dto.getDataVigencia());
        aditivo.setContrato(contrato);

        aditivo = aditivoRepo.save(aditivo);
        return AditivoContractualMapper.toDTO(aditivo);
    }

    @Override
    @Transactional(readOnly = true)
    public AditivoContractualResponseDTO buscarPorId(Long id) {
        AditivoContractual aditivo = aditivoRepo.findById(id)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException("Aditivo não encontrado com ID " + id)
                );
        return AditivoContractualMapper.toDTO(aditivo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AditivoContractualResponseDTO> listarPorContrato(Long idContrato) {
        List<AditivoContractual> lista = aditivoRepo.findAll().stream()
                .filter(a -> a.getContrato().getIdContrato().equals(idContrato))
                .collect(Collectors.toList());
        return lista.stream()
                .map(AditivoContractualMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public AditivoContractualResponseDTO atualizar(Long id, AditivoContractualCreateDTO dto) {
        AditivoContractual existente = aditivoRepo.findById(id)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException("Aditivo não encontrado com ID " + id)
                );

        Contrato contrato = contratoRepo.findById(dto.getContratoId())
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException("Contrato não encontrado com ID " + dto.getContratoId())
                );

        existente.setTipo(dto.getTipo());
        existente.setDescricaoMudancas(dto.getDescricaoMudancas());
        existente.setJustificativa(dto.getJustificativa());
        existente.setDataVigencia(dto.getDataVigencia());
        existente.setContrato(contrato);

        existente = aditivoRepo.save(existente);
        return AditivoContractualMapper.toDTO(existente);
    }

    @Override
    @Transactional
    public void deletar(Long id) {
        AditivoContractual aditivo = aditivoRepo.findById(id)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException("Aditivo não encontrado com ID " + id)
                );
        aditivoRepo.delete(aditivo);
    }
}
