package com.example.squad03.repository;

import com.example.squad03.enums.StatusContrato;
import com.example.squad03.model.Contrato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContratoRepository extends JpaRepository<Contrato, Long> {

    List<Contrato> findByEmpresa_IdOrgao(Long idOrgao); // Corrected method name

    List<Contrato> findByResponsavel_IdFuncionario(Long idFuncionario);


    List<Contrato> findByStatusContrato(StatusContrato statusContrato);
}
