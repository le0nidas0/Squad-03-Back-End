package com.example.squad03.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "empresa")
@Table(name = "Empresa")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrgao;

    @Column(unique = true, length = 18)
    private String cnpj;

    private String nomeFantasia;
    private String razaoSocial;
//    private String numeroEmpresa;
    private String estado;
    private String cidade;
    private String inscricaoMunicipal;
    private String tipoEmpresa;
    private String cep;
    private String bairro;
    private String logradouro;
    private String numero;
    private String complemento;
    private String email;
    private String telefone;

    @OneToMany(mappedBy = "empresa")
    @JsonIgnore
    private List<Contrato> contratos;

    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Representante> representantes = new ArrayList<>();

    public void addRepresentante(Representante representante) {
        representantes.add(representante);
        representante.setEmpresa(this);
    }
}


