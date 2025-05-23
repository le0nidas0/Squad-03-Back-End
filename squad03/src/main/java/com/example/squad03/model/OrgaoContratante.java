package com.example.squad03.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Entity(name = "OrgaoContratante")
@Table(name = "orgao_contratante")
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
    private String inscricaoMunicipal;
    private String tipoEmpresa;
    private String cep;
    private String bairro;
    private String logradouro;
    private String complemento;
    private String email;
    private String telefone;

    @OneToMany(mappedBy = "orgaoContratante")
    @JsonIgnore
    private List<Contrato> contratos;

    @OneToMany(mappedBy = "orgaoContratante", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Representante> representantes;
}


