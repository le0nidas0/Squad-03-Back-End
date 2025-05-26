package com.example.squad03.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Table(name = "aditivo_contratual")
@Entity(name = "AditivoContractual")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AditivoContractual {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAditivoContractual;

    @Column(name = "tipo", nullable = false)
    private String tipo;

    @Column(name = "justificativa", nullable = false, columnDefinition = "TEXT")
    private String justificativa;

    @Column(name = "criado_em", nullable = false)
    private LocalDateTime criadoEm;

    @ManyToOne
    @JoinColumn(name = "contrato_id", nullable = false)
    private Contrato contrato;

    @Column(name = "responsavel_id", nullable = false)
    private Long responsavelId;
}
