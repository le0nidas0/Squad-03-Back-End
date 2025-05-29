package com.example.squad03.repository;

import com.example.squad03.model.Entregavel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntregavelRepository extends JpaRepository<Entregavel, Long> {
    List<Entregavel> findAllByContrato_idContrato(Long idContrato);
    List<Entregavel> findAllByResponsavel_idFuncionario(Long idFuncionario);
}
