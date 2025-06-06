package com.example.squad03.service.impl;

import com.example.squad03.dto.EmpresaCreateDTO;
import com.example.squad03.dto.EmpresaResponseDTO;
import com.example.squad03.dto.RepresentanteCreateDTO;
import com.example.squad03.exception.DocumentoInvalidoException;
import com.example.squad03.exception.RecursoNaoEncontradoException;
import com.example.squad03.mapper.EmpresaMapper;
import com.example.squad03.model.Empresa;
import com.example.squad03.model.Representante;
import com.example.squad03.repository.EmpresaRepository;
import com.example.squad03.repository.RepresentanteRepository;
import com.example.squad03.service.EmpresaService;
import com.example.squad03.util.ValidadorDocumentoUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmpresaServiceImpl implements EmpresaService {

    private final EmpresaRepository repository;
    private final RepresentanteRepository representanteRepository;

    @Override
    public EmpresaResponseDTO criarOrgao(EmpresaCreateDTO dto) {
        if (!ValidadorDocumentoUtil.isCnpjValido(dto.getCnpj())) {
            throw new IllegalArgumentException("CNPJ inválido");
        }

        Empresa orgao = EmpresaMapper.toEntity(dto);

        if (dto.getRepresentantes() != null) {
            Empresa finalOrgao = orgao;
            dto.getRepresentantes().forEach(rdto -> {
                if (!ValidadorDocumentoUtil.isCpfValido(rdto.getCpf())) {
                    throw new IllegalArgumentException("CPF inválido do representante: " + rdto.getNome());
                }

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
                .orElseThrow(() -> new RecursoNaoEncontradoException("Empresa não encontrada com ID " + id));
        return EmpresaMapper.toDTO(orgao);
    }

    @Override
    public EmpresaResponseDTO atualizar(Long id, EmpresaCreateDTO dto) {
        Empresa existente = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Empresa não encontrada com ID " + id));

        if (!ValidadorDocumentoUtil.isCnpjValido(dto.getCnpj())) {
            throw new DocumentoInvalidoException("CNPJ inválido");
        }

        existente.setNomeFantasia(dto.getNomeFantasia());
        existente.setRazaoSocial(dto.getRazaoSocial());
        existente.setCnpj(dto.getCnpj());
        existente.setEstado(dto.getEstado());
        existente.setCidade(dto.getCidade());
        existente.setInscricaoMunicipal(dto.getInscricaoMunicipal());
        existente.setTipoEmpresa(dto.getTipoEmpresa());
        existente.setCep(dto.getCep());
        existente.setBairro(dto.getBairro());
        existente.setLogradouro(dto.getLogradouro());
        existente.setNumero(dto.getNumero());
        existente.setComplemento(dto.getComplemento());
        existente.setEmail(dto.getEmail());
        existente.setTelefone(dto.getTelefone());

        existente = repository.save(existente);

        if (dto.getRepresentantes() != null) {
            for (RepresentanteCreateDTO representanteDTO : dto.getRepresentantes()) {
                if (!ValidadorDocumentoUtil.isCpfValido(representanteDTO.getCpf())) {
                    throw new IllegalArgumentException("CPF inválido do representante: " + representanteDTO.getNome());
                }

                Representante representante;

                if (representanteDTO.getId() != null) {
                    representante = representanteRepository.findById(representanteDTO.getId())
                            .orElseThrow(() -> new EntityNotFoundException("Representante com ID " + representanteDTO.getId() + " não encontrado"));

                    representante.setNome(representanteDTO.getNome());
                    representante.setCpf(representanteDTO.getCpf());
                    representante.setEmail(representanteDTO.getEmail());
                    representante.setTelefone(representanteDTO.getTelefone());
                } else {
                    representante = new Representante();
                    representante.setNome(representanteDTO.getNome());
                    representante.setCpf(representanteDTO.getCpf());
                    representante.setEmail(representanteDTO.getEmail());
                    representante.setTelefone(representanteDTO.getTelefone());
                    representante.setEmpresa(existente);
                }

                representanteRepository.save(representante);
            }
        }

        return EmpresaMapper.toDTO(existente);
    }

    @Override
    public String deletar(Long id) {
        Empresa orgao = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Órgão contratante não encontrado com ID " + id));
        repository.delete(orgao);
        return "Empresa com ID " + id + " foi deletada com sucesso.";
    }
}
