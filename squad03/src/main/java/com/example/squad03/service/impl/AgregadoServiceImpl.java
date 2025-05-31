package com.example.squad03.service.impl;

import com.example.squad03.dto.AgregadoCreateDTO;
import com.example.squad03.dto.AgregadoResponseDTO;
import com.example.squad03.exception.RecursoNaoEncontradoException;
import com.example.squad03.mapper.AgregadoMapper;
import com.example.squad03.model.Agregado;
import com.example.squad03.model.Colaborador;
import com.example.squad03.model.Contrato;
import com.example.squad03.repository.AgregadoRepository;
import com.example.squad03.repository.ColaboradorRepository;
import com.example.squad03.repository.ContratoRepository;
import com.example.squad03.service.AgregadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AgregadoServiceImpl implements AgregadoService {

    private final AgregadoRepository agregadoRepo;
    private final ContratoRepository contratoRepo;
    private final ColaboradorRepository colaboradorRepo;

    @Override
    @Transactional
    public AgregadoResponseDTO criarAgregado(AgregadoCreateDTO dto) {
        Contrato contrato = contratoRepo.findById(dto.getContratoId())
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Contrato não encontrado com ID " + dto.getContratoId()
                ));

        Colaborador colaborador = colaboradorRepo.findById(dto.getColaboradorId())
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Colaborador não encontrado com ID " + dto.getColaboradorId()
                ));

        Agregado agregado = new Agregado();
        agregado.setFuncao(dto.getFuncao());
        agregado.setDataInicio(dto.getDataInicio());
        agregado.setDataFim(dto.getDataFim());
        agregado.setContrato(contrato);
        agregado.setColaborador(colaborador);

        agregado = agregadoRepo.save(agregado);
        return AgregadoMapper.toDTO(agregado);
    }

    @Override
    @Transactional(readOnly = true)
    public AgregadoResponseDTO buscarPorId(Long id) {
        Agregado agregado = agregadoRepo.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Agregado não encontrado com ID " + id
                ));
        return AgregadoMapper.toDTO(agregado);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AgregadoResponseDTO> listarPorContrato(Long contratoId) {
        List<Agregado> lista = agregadoRepo.findByContrato_idContrato(contratoId);
        return lista.stream()
                .map(AgregadoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<AgregadoResponseDTO> listarPorColaborador(Long idFuncionario) {
        List<Agregado> lista = agregadoRepo.findByColaborador_idFuncionario(idFuncionario);
        return lista.stream()
                .map(AgregadoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public AgregadoResponseDTO atualizar(Long id, AgregadoCreateDTO dto) {
        Agregado existente = agregadoRepo.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Agregado não encontrado com ID " + id
                ));

        Contrato contrato = contratoRepo.findById(dto.getContratoId())
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Contrato não encontrado com ID " + dto.getContratoId()
                ));

        Colaborador colaborador = colaboradorRepo.findById(dto.getColaboradorId())
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Colaborador não encontrado com ID " + dto.getColaboradorId()
                ));

        existente.setFuncao(dto.getFuncao());
        existente.setDataInicio(dto.getDataInicio());
        existente.setDataFim(dto.getDataFim());
        existente.setContrato(contrato);
        existente.setColaborador(colaborador);

        existente = agregadoRepo.save(existente);
        return AgregadoMapper.toDTO(existente);
    }

    @Override
    @Transactional
    public void deletar(Long id) {
        Agregado agregado = agregadoRepo.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Agregado não encontrado com ID " + id
                ));
        agregadoRepo.delete(agregado);
    }
}
