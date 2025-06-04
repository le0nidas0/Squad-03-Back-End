package com.example.squad03.repository;

import com.example.squad03.model.AditivoDocumento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AditivoDocumentoRepository extends JpaRepository<AditivoDocumento, Long> {
    List<AditivoDocumento> findByAditivoIdAditivoContractual(Long aditivoId);
}
