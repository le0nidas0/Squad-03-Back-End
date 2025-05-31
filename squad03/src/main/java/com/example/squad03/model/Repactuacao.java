package com.example.squad03.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "repactuacao")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Repactuacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRepactuacao;

    @Column(name = "indice", nullable = false, length = 50)
    private String indice;

    @Column(name = "data_base", nullable = false)
    private LocalDate dataBase;

    @Column(name = "valor_anterior", nullable = false)
    private BigDecimal valorAnterior;

    @Column(name = "valor_repactuado", nullable = false)
    private BigDecimal valorRepactuado;

    @Column(name = "justificativa", columnDefinition = "TEXT", nullable = false)
    private String justificativa;

    @CreatedDate
    @Column(name = "criado_em", nullable = false, updatable = false)
    private LocalDateTime criadoEm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contrato_id", nullable = false)
    private Contrato contrato;
}
