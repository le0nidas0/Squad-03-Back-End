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

        OrgaoContratante orgao = new OrgaoContratante();
        orgao.setNome(dto.getNome());
        orgao.setCnpj(dto.getCnpj());
        orgao.setNomeFantasia(dto.getNomeFantasia());
        orgao.setRazaoSocial(dto.getRazaoSocial());
        orgao.setNumeroEmpresa(dto.getNumeroEmpresa());
        orgao.setEstado(dto.getEstado());
        orgao.setCidade(dto.getCidade());
        orgao.setNomeRepresentante(dto.getNomeRepresentante());
        orgao.setCpfResponsavel(dto.getCpfResponsavel());
        orgao.setNumeroRepresentante(dto.getNumeroRepresentante());
        orgao.setEmailRepresentante(dto.getEmailRepresentante());

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
        OrgaoContratante orgao = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Órgão contratante não encontrado com ID " + id));

        if (!ValidadorDocumentoUtil.isCnpjValido(dto.getCnpj())) {
            throw new IllegalArgumentException("CNPJ inválido");
        }

        orgao.setNome(dto.getNome());
        orgao.setCnpj(dto.getCnpj());
        orgao.setNomeFantasia(dto.getNomeFantasia());
        orgao.setRazaoSocial(dto.getRazaoSocial());
        orgao.setNumeroEmpresa(dto.getNumeroEmpresa());
        orgao.setEstado(dto.getEstado());
        orgao.setCidade(dto.getCidade());
        orgao.setNomeRepresentante(dto.getNomeRepresentante());
        orgao.setCpfResponsavel(dto.getCpfResponsavel());
        orgao.setNumeroRepresentante(dto.getNumeroRepresentante());
        orgao.setEmailRepresentante(dto.getEmailRepresentante());

        orgao = repository.save(orgao);
        return OrgaoContratanteMapper.toDTO(orgao);
    }

    @Override
    public void deletar(Long id) {
        OrgaoContratante orgao = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Órgão contratante não encontrado com ID " + id));
        repository.delete(orgao);
    }

}
