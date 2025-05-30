package com.example.squad03.repository;

import com.example.squad03.model.Documento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentoRepository  extends JpaRepository<Documento, Long> {
    List<Documento> findAllByContrato_idContrato(Long idContrato);
}
