package com.example.squad03.model;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table(name = "orgao_contratante")
@Entity(name = "OrgaoContratante")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrgaoContratante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrgao;

    private String nome;

    @Column(unique = true, length = 18)
    private String cnpj;

    private String nomeFantasia;
    private String razaoSocial;
    private String numeroEmpresa;
    private String estado;
    private String cidade;

    private String nomeRepresentante;
    private String cpfResponsavel;
    private String numeroRepresentante;
    private String emailRepresentante;

    @OneToMany(mappedBy = "orgaoContratante")
    private List<Contrato> contratos;
}

