package com.example.squad03.repository;

import com.example.squad03.model.Repactuacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepactuacaoRepository extends JpaRepository<Repactuacao, Long> {
    List<Repactuacao> findByContrato_idContrato(Long idContrato);
}
