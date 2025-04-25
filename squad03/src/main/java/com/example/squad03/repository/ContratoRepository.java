package com.example.squad03.repository;

import com.example.squad03.model.Contrato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContratoRepository extends JpaRepository<Contrato, Long> {

    List<Contrato> findByOrgaoContratante_IdOrgao(Long idOrgao);

    List<Contrato> findByResponsavel_IdFuncionario(Long idFuncionario);

    List<Contrato> findByRepresentante_IdFuncionario(Long idFuncionario);

    List<Contrato> findByStatus(String status);
}
