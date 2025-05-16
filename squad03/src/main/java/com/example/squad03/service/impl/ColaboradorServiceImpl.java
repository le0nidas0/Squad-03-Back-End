package com.example.squad03.service.impl;
import com.example.squad03.dto.ColaboradorCreateDTO;
import com.example.squad03.dto.ColaboradorResponseDTO;
import com.example.squad03.exception.RecursoNaoEncontradoException;
import com.example.squad03.mapper.ColaboradorMapper;
import com.example.squad03.model.Colaborador;
import com.example.squad03.repository.ColaboradorRepository;
import com.example.squad03.service.ColaboradorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ColaboradorServiceImpl implements ColaboradorService {

    private final ColaboradorRepository colaboradorRepository;

    @Override
    public ColaboradorResponseDTO criar(ColaboradorCreateDTO dto) {
        Colaborador colaborador = ColaboradorMapper.toEntity(dto);
        colaborador = colaboradorRepository.save(colaborador);
        return ColaboradorMapper.toDTO(colaborador);
    }

    @Override
    public List<ColaboradorResponseDTO> listarTodos() {
        return colaboradorRepository.findAll()
                .stream()
                .map(ColaboradorMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ColaboradorResponseDTO buscarPorId(Long id) {
        Colaborador colaborador = colaboradorRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Colaborador não encontrado com ID " + id));
        return ColaboradorMapper.toDTO(colaborador);
    }

    @Override
    public void deletar(Long id) {
        Colaborador colaborador = colaboradorRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Colaborador não encontrado com ID " + id));
        colaboradorRepository.delete(colaborador);
    }

    @Override
    public ColaboradorResponseDTO atualizar(Long id, ColaboradorCreateDTO dto) {
        Colaborador colaborador = colaboradorRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Colaborador não encontrado com ID " + id));

        colaborador.setNome(dto.getNome());
        colaborador.setEmail(dto.getEmail());
        colaborador.setCpf(dto.getCpf());
        colaborador.setCargo(dto.getCargo());
        colaborador.setTelefone(dto.getTelefone());

        colaborador = colaboradorRepository.save(colaborador);
        return ColaboradorMapper.toDTO(colaborador);
    }
}
