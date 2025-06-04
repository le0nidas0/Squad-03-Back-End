package com.example.squad03.repository;

import com.example.squad03.model.AditivoContractual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AditivoContractualRepository extends JpaRepository<AditivoContractual, Long> {
    List<AditivoContractual> findAllByContrato_idContrato(Long idContrato);
}