package com.example.squad03.service.impl;

import com.example.squad03.dto.ContratoCreateDTO;
import com.example.squad03.dto.ContratoResponseDTO;
import com.example.squad03.dto.FuncionarioResponseDTO;
import com.example.squad03.dto.OrgaoContratanteResponseDTO;
import com.example.squad03.exception.RecursoNaoEncontradoException;
import com.example.squad03.model.Contrato;
import com.example.squad03.model.Funcionario;
import com.example.squad03.model.OrgaoContratante;
import com.example.squad03.repository.ContratoRepository;
import com.example.squad03.repository.FuncionarioRepository;
import com.example.squad03.repository.OrgaoContratanteRepository;
import com.example.squad03.service.ContratoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContratoServiceImpl implements ContratoService {

    private final ContratoRepository contratoRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final OrgaoContratanteRepository orgaoRepository;

    @Override
    public ContratoResponseDTO criarContrato(ContratoCreateDTO dto) {
        Funcionario responsavel = funcionarioRepository.findById(dto.getResponsavelId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Responsável não encontrado."));

        Funcionario representante = funcionarioRepository.findById(dto.getRepresentanteId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Representante não encontrado."));

        OrgaoContratante orgao = orgaoRepository.findById(dto.getOrgaoContratanteId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Órgão contratante não encontrado."));

        Contrato contrato = new Contrato();
        contrato.setPrazo(dto.getPrazo());
        contrato.setStatus(dto.getStatus());
        contrato.setValor(dto.getValor());
        contrato.setCriadoEm(LocalDateTime.now());
        contrato.setOrgaoContratante(orgao);
        contrato.setResponsavel(responsavel);
        contrato.setRepresentante(representante);

        contrato = contratoRepository.save(contrato);
        return toDTO(contrato);
    }

    @Override
    public ContratoResponseDTO buscarPorId(Long id) {
        return contratoRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Contrato não encontrado com ID " + id));
    }

    @Override
    public List<ContratoResponseDTO> listarTodos() {
        return contratoRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deletar(Long id) {
        Contrato contrato = contratoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Contrato não encontrado com ID " + id));
        contratoRepository.delete(contrato);
    }

    @Override
    public ContratoResponseDTO atualizar(Long id, ContratoCreateDTO dto) {
        Contrato contrato = contratoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Contrato não encontrado com ID " + id));

        Funcionario responsavel = funcionarioRepository.findById(dto.getResponsavelId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Responsável não encontrado."));

        Funcionario representante = funcionarioRepository.findById(dto.getRepresentanteId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Representante não encontrado."));

        OrgaoContratante orgao = orgaoRepository.findById(dto.getOrgaoContratanteId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Órgão contratante não encontrado."));

        contrato.setPrazo(dto.getPrazo());
        contrato.setStatus(dto.getStatus());
        contrato.setValor(dto.getValor());
        contrato.setResponsavel(responsavel);
        contrato.setRepresentante(representante);
        contrato.setOrgaoContratante(orgao);

        contrato = contratoRepository.save(contrato);
        return toDTO(contrato);
    }

    private ContratoResponseDTO toDTO(Contrato contrato) {
        ContratoResponseDTO dto = new ContratoResponseDTO();
        dto.setIdContrato(contrato.getIdContrato());
        dto.setPrazo(contrato.getPrazo());
        dto.setStatus(contrato.getStatus());
        dto.setValor(contrato.getValor());
        dto.setCriadoEm(contrato.getCriadoEm());

        OrgaoContratanteResponseDTO orgaoDTO = new OrgaoContratanteResponseDTO();
        orgaoDTO.setId(contrato.getOrgaoContratante().getIdOrgao());
        orgaoDTO.setNome(contrato.getOrgaoContratante().getNome());
        dto.setOrgaoContratante(orgaoDTO);

        FuncionarioResponseDTO responsavelDTO = new FuncionarioResponseDTO();
        responsavelDTO.setIdFuncionario(contrato.getResponsavel().getIdFuncionario());
        responsavelDTO.setNome(contrato.getResponsavel().getNome());
        responsavelDTO.setCargo(contrato.getResponsavel().getCargo());
        dto.setResponsavel(responsavelDTO);

        FuncionarioResponseDTO representanteDTO = new FuncionarioResponseDTO();
        representanteDTO.setIdFuncionario(contrato.getRepresentante().getIdFuncionario());
        representanteDTO.setNome(contrato.getRepresentante().getNome());
        representanteDTO.setCargo(contrato.getRepresentante().getCargo());
        dto.setRepresentante(representanteDTO);

        return dto;
    }
}