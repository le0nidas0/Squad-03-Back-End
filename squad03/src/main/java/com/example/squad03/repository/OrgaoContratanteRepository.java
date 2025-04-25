package com.example.squad03.repository;

import com.example.squad03.model.OrgaoContratante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrgaoContratanteRepository extends JpaRepository<OrgaoContratante, Long> {

    Optional<OrgaoContratante> findByCnpj(String cnpj);

    Optional<OrgaoContratante> findByNomeFantasia(String nomeFantasia);
}
