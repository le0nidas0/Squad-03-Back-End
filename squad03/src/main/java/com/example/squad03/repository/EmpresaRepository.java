package com.example.squad03.repository;

import com.example.squad03.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

    Optional<Empresa> findByCnpj(String cnpj);

    Optional<Empresa> findByNomeFantasia(String nomeFantasia);
}
