package com.devinhouse.M02S10.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="JORNALISTAS")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Jornalista {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
//    @Column(nullable = false)
    private String id_usuario;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private Float salario;
    @Column(nullable = false)
    private String nivelDeCargo;
    @Column(nullable = false)
    private Integer numeroDePublicacoes;

}
