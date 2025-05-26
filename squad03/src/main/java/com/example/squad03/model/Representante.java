package com.example.squad03.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "representante")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Representante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String cpf;

    private String email;

    private String telefone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orgao_contratante_id", nullable = false)
    private OrgaoContratante orgaoContratante;
}
