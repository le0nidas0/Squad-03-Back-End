package com.example.squad03.service.impl;

import com.example.squad03.dto.OrgaoContratanteCreateDTO;
import com.example.squad03.dto.OrgaoContratanteResponseDTO;
import com.example.squad03.exception.RecursoNaoEncontradoException;
import com.example.squad03.mapper.OrgaoContratanteMapper;
import com.example.squad03.model.OrgaoContratante;
import com.example.squad03.repository.OrgaoContratanteRepository;
import com.example.squad03.service.OrgaoContratanteService;
import com.example.squad03.util.ValidadorDocumentoUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrgaoContratanteServiceImpl implements OrgaoContratanteService {

    private final OrgaoContratanteRepository repository;

    @Override
    public OrgaoContratanteResponseDTO criarOrgao(OrgaoContratanteCreateDTO dto) {
        if (!ValidadorDocumentoUtil.isCnpjValido(dto.getCnpj())) {
            throw new IllegalArgumentException("CNPJ inválido");
        }

        OrgaoContratante orgao = OrgaoContratanteMapper.toEntity(dto);
        orgao = repository.save(orgao);
        return OrgaoContratanteMapper.toDTO(orgao);
    }

    @Override
    public List<OrgaoContratanteResponseDTO> listarTodos() {
        return repository.findAll().stream()
                .map(OrgaoContratanteMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OrgaoContratanteResponseDTO buscarPorId(Long id) {
        OrgaoContratante orgao = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Órgão contratante não encontrado com ID " + id));
        return OrgaoContratanteMapper.toDTO(orgao);
    }

    @Override
    public OrgaoContratanteResponseDTO atualizar(Long id, OrgaoContratanteCreateDTO dto) {
        OrgaoContratante existente = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Órgão contratante não encontrado com ID " + id));

        existente.setNome(dto.getNome());
        existente.setNomeFantasia(dto.getNomeFantasia());
        existente.setRazaoSocial(dto.getRazaoSocial());
        existente.setCnpj(dto.getCnpj());
        existente.setNumeroEmpresa(dto.getNumeroEmpresa());
        existente.setEstado(dto.getEstado());
        existente.setCidade(dto.getCidade());
        existente.setNomeRepresentante(dto.getNomeRepresentante());
        existente.setCpfRepresentante(dto.getCpfRepresentante());
        existente.setEmailRepresentante(dto.getEmailRepresentante());
        existente.setNumeroRepresentante(dto.getNumeroRepresentante());

        return OrgaoContratanteMapper.toDTO(existente);
    }

    @Override
    public void deletar(Long id) {
        OrgaoContratante orgao = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Órgão contratante não encontrado com ID " + id));
        repository.delete(orgao);
    }
}
