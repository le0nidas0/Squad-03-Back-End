package com.example.squad03.service.impl;

import com.example.squad03.dto.FuncionarioCreateDTO;
import com.example.squad03.dto.FuncionarioResponseDTO;
import com.example.squad03.exception.RecursoNaoEncontradoException;
import com.example.squad03.mapper.FuncionarioMapper;
import com.example.squad03.model.Funcionario;
import com.example.squad03.repository.FuncionarioRepository;
import com.example.squad03.service.FuncionarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FuncionarioServiceImpl implements FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    @Override
    public FuncionarioResponseDTO criar(FuncionarioCreateDTO dto) {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(dto.getNome());
        funcionario.setEmail(dto.getEmail());
        funcionario.setCpf(dto.getCpf());
        funcionario.setCargo(dto.getCargo());
        funcionario.setTelefone(dto.getTelefone());

        funcionario = funcionarioRepository.save(funcionario);

        return FuncionarioMapper.toDTO(funcionario);
    }

    @Override
    public List<FuncionarioResponseDTO> listarTodos() {
        return funcionarioRepository.findAll()
                .stream()
                .map(FuncionarioMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public FuncionarioResponseDTO buscarPorId(Long id) {
        Funcionario funcionario = funcionarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Funcionário não encontrado com ID " + id));

        return FuncionarioMapper.toDTO(funcionario);
    }

    @Override
    public void deletar(Long id) {
        Funcionario funcionario = funcionarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Funcionário não encontrado com ID " + id));

        funcionarioRepository.delete(funcionario);
    }

    @Override
    public FuncionarioResponseDTO atualizar(Long id, FuncionarioCreateDTO dto) {
        Funcionario funcionario = funcionarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Funcionário não encontrado com ID " + id));

        funcionario.setNome(dto.getNome());
        funcionario.setEmail(dto.getEmail());
        funcionario.setCpf(dto.getCpf());
        funcionario.setCargo(dto.getCargo());
        funcionario.setTelefone(dto.getTelefone());

        funcionario = funcionarioRepository.save(funcionario);
        return FuncionarioMapper.toDTO(funcionario);
    }
}