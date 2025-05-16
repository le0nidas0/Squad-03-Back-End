package com.example.squad03.service.impl;

import com.example.squad03.dto.RepresentanteCreateDTO;
import com.example.squad03.dto.RepresentanteResponseDTO;
import com.example.squad03.exception.RecursoNaoEncontradoException;
import com.example.squad03.mapper.RepresentanteMapper;
import com.example.squad03.model.OrgaoContratante;
import com.example.squad03.model.Representante;
import com.example.squad03.repository.OrgaoContratanteRepository;
import com.example.squad03.repository.RepresentanteRepository;
import com.example.squad03.service.RepresentanteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RepresentanteServiceImpl implements RepresentanteService {

    private final RepresentanteRepository representanteRepository;
    private final OrgaoContratanteRepository orgaoContratanteRepository;

    @Override
    public RepresentanteResponseDTO criar(RepresentanteCreateDTO dto) {
        OrgaoContratante orgao = orgaoContratanteRepository.findById(dto.getIdOrgaoContratante())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Órgão não encontrado"));

        Representante representante = RepresentanteMapper.toEntity(dto, orgao);
        representante = representanteRepository.save(representante);

        return RepresentanteMapper.toDTO(representante);
    }

    @Override
    public List<RepresentanteResponseDTO> listar() {
        return representanteRepository.findAll().stream()
                .map(RepresentanteMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public RepresentanteResponseDTO buscarPorId(Long id) {
        Representante representante = representanteRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Representante não encontrado"));
        return RepresentanteMapper.toDTO(representante);
    }
}
