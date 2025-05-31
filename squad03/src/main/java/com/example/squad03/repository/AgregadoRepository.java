package com.example.squad03.repository;

import com.example.squad03.model.Agregado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AgregadoRepository extends JpaRepository<Agregado, Long> {
    List<Agregado> findByContrato_idContrato(Long idContrato);
    List<Agregado> findByColaborador_idFuncionario(Long idFuncionario);
}
