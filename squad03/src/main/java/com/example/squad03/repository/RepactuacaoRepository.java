package com.example.squad03.repository;

import com.example.squad03.model.Repactuacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepactuacaoRepository extends JpaRepository<Repactuacao, Long> {
    List<Repactuacao> findByContrato_idContrato(Long idContrato);
}
