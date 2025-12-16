package com.tcc.oficina_app.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "funcionarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Inheritance(strategy = InheritanceType.JOINED)

public class Funcionario{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_funcionario")
    @EqualsAndHashCode.Include
    private Integer idFuncionario;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(length = 50)
    private String cargo;

    @Column(nullable = false, length = 50, unique = true)
    private String login;

    @Column(nullable = false, length = 100)
    private String senha;

}