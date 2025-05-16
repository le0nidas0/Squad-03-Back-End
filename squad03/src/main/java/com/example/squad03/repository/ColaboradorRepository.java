package com.example.squad03.repository;

import com.example.squad03.model.Colaborador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ColaboradorRepository extends JpaRepository<Colaborador, Long> {

    Optional<Colaborador> findByCpf(String cpf);

    Optional<Colaborador> findByEmail(String email);
}
