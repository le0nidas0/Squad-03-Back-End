package com.example.squad03.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "aditivo_contratual")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AditivoContractual {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAditivoContractual;

    @Column(name = "tipo", nullable = false, length = 50)
    private String tipo;

    @Column(name = "descricao_mudancas", columnDefinition = "TEXT", nullable = false)
    private String descricaoMudancas;

    @Column(name = "justificativa", columnDefinition = "TEXT", nullable = false)
    private String justificativa;

    @Column(name = "data_vigencia", nullable = false)
    private LocalDate dataVigencia;

    @CreatedDate
    @Column(name = "criado_em", nullable = false, updatable = false)
    private LocalDateTime criadoEm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idContrato", nullable = false)
    private Contrato contrato;

    // Relacionamento One-to-Many para os arquivos deste aditivo
    @OneToMany(
            mappedBy = "aditivo",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<AditivoDocumento> documentos = new ArrayList<>();

    // Helper para manter bidirecionalidade
    public void addDocumento(AditivoDocumento doc) {
        documentos.add(doc);
        doc.setAditivo(this);
    }

    public void removeDocumento(AditivoDocumento doc) {
        documentos.remove(doc);
        doc.setAditivo(null);
    }
}