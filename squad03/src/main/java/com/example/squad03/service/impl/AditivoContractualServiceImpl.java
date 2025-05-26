package com.example.squad03.service.impl;

import com.example.squad03.dto.AditivoContractualCreateDTO;
import com.example.squad03.dto.AditivoContractualResponseDTO;
import com.example.squad03.exception.RecursoNaoEncontradoException;
import com.example.squad03.mapper.AditivoContractualMapper;
import com.example.squad03.model.AditivoContractual;
import com.example.squad03.model.Colaborador;
import com.example.squad03.model.Contrato;
import com.example.squad03.repository.AditivoContractualRepository;
import com.example.squad03.repository.ColaboradorRepository;
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

    private final AditivoContractualRepository repo;
    private final ContratoRepository contratoRepo;
    private final ColaboradorRepository colaboradorRepo;

    @Override
    @Transactional
    public AditivoContractualResponseDTO criarAditivo(AditivoContractualCreateDTO dto) {
        Contrato contrato = contratoRepo.findById(dto.getContratoId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Contrato não encontrado com ID " + dto.getContratoId()));

        Colaborador responsavel = colaboradorRepo.findById(dto.getResponsavelId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Responsável não encontrado com ID " + dto.getResponsavelId()));

        AditivoContractual entidade = AditivoContractualMapper.toEntity(dto, contrato, responsavel);
        entidade = repo.save(entidade);

        return AditivoContractualMapper.toDTO(entidade, responsavel);
    }

    @Override
    public AditivoContractualResponseDTO buscarPorId(Long id) {
        AditivoContractual ent = repo.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Aditivo não encontrado com ID " + id));

        Colaborador responsavel = colaboradorRepo.findById(ent.getResponsavelId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Responsável não encontrado com ID " + ent.getResponsavelId()));

        return AditivoContractualMapper.toDTO(ent, responsavel);
    }

    @Override
    public List<AditivoContractualResponseDTO> listarTodos() {
        return repo.findAll().stream()
                .map(ent -> {
                    Colaborador resp = colaboradorRepo.findById(ent.getResponsavelId())
                            .orElseThrow(() -> new RecursoNaoEncontradoException("Responsável não encontrado com ID " + ent.getResponsavelId()));
                    return AditivoContractualMapper.toDTO(ent, resp);
                })
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public AditivoContractualResponseDTO atualizar(Long id, AditivoContractualCreateDTO dto) {
        AditivoContractual existente = repo.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Aditivo não encontrado com ID " + id));

        Contrato contrato = contratoRepo.findById(dto.getContratoId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Contrato não encontrado com ID " + dto.getContratoId()));
        Colaborador responsavel = colaboradorRepo.findById(dto.getResponsavelId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Responsável não encontrado com ID " + dto.getResponsavelId()));

        existente.setTipo(dto.getTipo());
        existente.setJustificativa(dto.getJustificativa());
        existente.setContrato(contrato);
        existente.setResponsavelId(responsavel.getIdFuncionario());

        existente = repo.save(existente);
        return AditivoContractualMapper.toDTO(existente, responsavel);
    }

    @Override
    @Transactional
    public void deletar(Long id) {
        AditivoContractual ent = repo.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Aditivo não encontrado com ID " + id));
        repo.delete(ent);
    }
}
