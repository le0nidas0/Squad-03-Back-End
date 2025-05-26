package com.example.squad03.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Table(name = "entregavel")
@Entity(name = "Entregavel")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Entregavel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEntregavel;

    @Column(name = "descricao", nullable = false, columnDefinition = "TEXT")
    private String descricao;

    @Column(name = "prazo_entrega", nullable = false)
    private LocalDate prazoEntrega;

    @Column(name = "status", nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "contrato_id", nullable = false)
    private Contrato contrato;
}
