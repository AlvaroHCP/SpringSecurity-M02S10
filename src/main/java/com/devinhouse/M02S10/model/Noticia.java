package com.devinhouse.M02S10.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name="NOTICIAS")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Noticia {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String titulo;
    @Column(nullable = false)
    private String textoNoticia;
//    @Column(nullable = false)
    private Long id_revisor;
//    @Column(nullable = false)
    private Long id_jornalista;
    @Column(nullable = false)
    private LocalDateTime dataCriacao;
    @Column(nullable = false)
    private LocalDateTime dataAtualizacao;
}

