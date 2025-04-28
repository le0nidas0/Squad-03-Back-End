package com.example.squad03.model;
import com.example.squad03.enums.StatusContrato;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Table(name = "contrato")
@Entity(name = "Contrato")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Contrato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idContrato;

    private LocalDate prazo;
    private BigDecimal valor;

    @Column(name = "criado_em")
    private LocalDateTime criadoEm;

    @Enumerated(EnumType.STRING)
    private StatusContrato status;

    @ManyToOne
    @JoinColumn(name = "orgao_contratante_id")
    private OrgaoContratante orgaoContratante;

    @ManyToOne
    @JoinColumn(name = "id_responsavel")
    private Funcionario responsavel;

    @ManyToOne
    @JoinColumn(name = "id_representante")
    private Funcionario representante;

 @OneToMany(mappedBy = "contrato")
    private List<Entregavel> entregaveis;

    @OneToMany(mappedBy = "contrato")
    private List<AditivoContratual> aditivos;

    @OneToMany(mappedBy = "contrato")
    private List<Notificacao> notificacoes;

    @OneToMany(mappedBy = "contrato")
    private List<Documento> documentos;
}
