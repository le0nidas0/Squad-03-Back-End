package com.example.squad03.repository;

import com.example.squad03.model.Documento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentoRepository  extends JpaRepository<Documento, Long> {
    List<Documento> findAllByContrato_idContrato(Long idContrato);
}
