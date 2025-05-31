package com.example.squad03.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "aditivo_documento")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AditivoDocumento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDocumento;

    @Column(name = "nome_arquivo", nullable = false, length = 150)
    private String nomeArquivo;

    @Lob
    @Column(name = "dados", columnDefinition = "LONGBLOB", nullable = false)
    private byte[] dados;

    @Column(name = "mime_type", length = 100, nullable = false)
    private String mimeType;

    @Column(name = "tamanho_bytes", nullable = false)
    private Long tamanho;

    @CreatedDate
    @Column(name = "criado_em", nullable = false, updatable = false)
    private LocalDateTime criadoEm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idAditivoContractual", nullable = false)
    private AditivoContractual aditivo;
}