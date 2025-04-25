package com.example.squad03.model;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table(name = "funcionario")
@Entity(name = "Funcionario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFuncionario;

    private String nome;

    @Column(unique = true)
    private String email;

    @Column(unique = true, length = 14)
    private String cpf;

    private String cargo;

    private String telefone;

    @OneToMany(mappedBy = "responsavel")
    private List<Contrato> contratosResponsaveis;

    @OneToMany(mappedBy = "representante")
    private List<Contrato> contratoRepresentantes;
}

