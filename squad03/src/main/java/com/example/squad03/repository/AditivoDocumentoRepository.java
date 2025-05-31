package com.example.squad03.repository;

import com.example.squad03.model.AditivoDocumento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AditivoDocumentoRepository extends JpaRepository<AditivoDocumento, Long> {
    List<AditivoDocumento> findByAditivoIdAditivoContractual(Long aditivoId);
}
