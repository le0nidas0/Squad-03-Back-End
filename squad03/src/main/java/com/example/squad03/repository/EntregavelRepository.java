package com.example.squad03.repository;

import com.example.squad03.model.Entregavel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntregavelRepository extends JpaRepository<Entregavel, Long> {
}
