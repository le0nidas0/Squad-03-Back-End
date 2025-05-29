package com.example.squad03.model;

import com.example.squad03.enums.StatusContrato;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "contrato")
@Entity(name = "Contrato")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Contrato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idContrato;

    @Column(name = "numero_contrato", nullable = false, unique = true, length = 50)
    private String numeroContrato;

    @Column(name = "descricao", columnDefinition = "TEXT")
    private String descricao;

    @Column(name = "data_inicio", nullable = false)
    private LocalDate dataInicio;

    @Column(name = "data_fim", nullable = false)
    private LocalDate dataFim;

    @Column(name = "termos_pagamento", length = 100)
    private String termosDePagamento;

    private BigDecimal valorContrato;

    @Column(name = "valor_total_pago")
    private BigDecimal valorTotalPago;

    @Column(name = "valor_total_pendente")
    private BigDecimal valorTotalPendente;

    @Column(name = "auto_renovacao")
    private Boolean autoRenovacao;

    @Column(name = "dias_para_cancelamento")
    private Integer diasParaCancelamento;

    @Column(name = "motivo_cancelamento", columnDefinition = "TEXT")
    private String motivoCancelamento;

    @CreatedDate
    @Column(name = "criado_em", nullable = false, updatable = false)
    private LocalDateTime criadoEm;

    @LastModifiedDate
    @Column(name = "atualizado_em")
    private LocalDateTime atualizadoEm;

    @Column(name = "criado_por", updatable = false)
    private String criadoPor;

    @Column(name = "atualizado_por")
    private String atualizadoPor;

    @Enumerated(EnumType.STRING)
    private StatusContrato statusContrato;

    @Column(name = "tipo_contrato", length = 50)
    private String tipoContrato;

    @Column(name = "tags", length = 255)
    private String tags;

    @ManyToOne
    @JoinColumn(name = "id_orgao", nullable = false)
    private Empresa empresa;

    @ManyToOne
    @JoinColumn(name = "id_funcionario", nullable = false)
    private Colaborador responsavel;

    // campo de Representante comentado se não for mais usado
    // @ManyToOne
    // @JoinColumn(name = "id_representante")
    // private Representante representante;

    @OneToMany(mappedBy = "contrato", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Entregavel> entregaveis = new ArrayList<>();

    @OneToMany(mappedBy = "contrato", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AditivoContractual> aditivos = new ArrayList<>();

    // notificações e documentos reativados, se quiser
    // @OneToMany(mappedBy = "contrato", cascade = CascadeType.ALL, orphanRemoval = true)
    // private List<Notificacao> notificacoes = new ArrayList<>();

    // @OneToMany(mappedBy = "contrato", cascade = CascadeType.ALL, orphanRemoval = true)
    // private List<Documento> documentos = new ArrayList<>();

    public void addEntregavel(Entregavel e) {
        entregaveis.add(e);
        e.setContrato(this);
    }

    public void removeEntregavel(Entregavel e) {
        entregaveis.remove(e);
        e.setContrato(null);
    }

    public void addAditivo(AditivoContractual a) {
        aditivos.add(a);
        a.setContrato(this);
    }

    public void removeAditivo(AditivoContractual a) {
        aditivos.remove(a);
        a.setContrato(null);
    }
}
