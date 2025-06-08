package com.example.squad03.repository;

import com.example.squad03.model.Representante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepresentanteRepository extends JpaRepository<Representante, Long> {
    List<Representante>findAllByEmpresa_idOrgao(Long idOrgao);
}
