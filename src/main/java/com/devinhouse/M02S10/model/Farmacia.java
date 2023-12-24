package com.devinhouse.M02S10.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="FARMACIAS")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Farmacia {
    @Id
    @Column(nullable = false)
    private Long cnpj;
    @Column(nullable = false)
    private String razaoSocial;
    @Column(nullable = false)
    private String nomeFantasia;
    @Column(nullable = false)
    private String email;
    private String telefone;
    @Column(nullable = false)
    private String celular;
    @Embedded
    private Endereco endereco;
}
