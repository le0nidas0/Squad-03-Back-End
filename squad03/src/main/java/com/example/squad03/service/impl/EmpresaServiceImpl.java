package com.example.squad03.service.impl;

import com.example.squad03.dto.EmpresaCreateDTO;
import com.example.squad03.dto.EmpresaResponseDTO;
import com.example.squad03.exception.RecursoNaoEncontradoException;
import com.example.squad03.mapper.EmpresaMapper;
import com.example.squad03.model.Empresa;
import com.example.squad03.model.Representante;
import com.example.squad03.repository.EmpresaRepository;
import com.example.squad03.service.EmpresaService;
import com.example.squad03.util.ValidadorDocumentoUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmpresaServiceImpl implements EmpresaService {

    private final EmpresaRepository repository;

    @Override
    public EmpresaResponseDTO criarOrgao(EmpresaCreateDTO dto) {
        if (!ValidadorDocumentoUtil.isCnpjValido(dto.getCnpj())) {
            throw new IllegalArgumentException("CNPJ inválido");
        }

        Empresa orgao = EmpresaMapper.toEntity(dto);

        if (dto.getRepresentantes() != null) {
            Empresa finalOrgao = orgao;
            dto.getRepresentantes().forEach(rdto -> {
                Representante rep = new Representante();
                rep.setNome(rdto.getNome());
                rep.setCpf(rdto.getCpf());
                rep.setEmail(rdto.getEmail());
                rep.setTelefone(rdto.getTelefone());
                finalOrgao.addRepresentante(rep);
            });
        }

        orgao = repository.save(orgao);

        return EmpresaMapper.toDTO(orgao);
    }

    @Override
    public List<EmpresaResponseDTO> listarTodos() {
        return repository.findAll().stream()
                .map(EmpresaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EmpresaResponseDTO buscarPorId(Long id) {
        Empresa orgao = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Empresa não encontrado com ID " + id));
        return EmpresaMapper.toDTO(orgao);
    }

    @Override
    public EmpresaResponseDTO atualizar(Long id, EmpresaCreateDTO dto) {
        Empresa existente = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Empresa não encontrada com ID " + id));

        existente.setNomeFantasia(dto.getNomeFantasia());
        existente.setRazaoSocial(dto.getRazaoSocial());
        existente.setNumeroEmpresa(dto.getNumeroEmpresa());
        existente.setEstado(dto.getEstado());
        existente.setCidade(dto.getCidade());
        existente = repository.save(existente);

        return EmpresaMapper.toDTO(existente);
    }

    @Override
    public String deletar(Long id) {
        Empresa orgao = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Órgão contratante não encontrado com ID " + id));
        repository.delete(orgao);
        return "Empresa com ID " + id + " foi deletado com sucesso.";
    }

}
