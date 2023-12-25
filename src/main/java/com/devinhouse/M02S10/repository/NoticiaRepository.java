package com.devinhouse.M02S10.repository;

import com.devinhouse.M02S10.model.Noticia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NoticiaRepository extends JpaRepository<Noticia, Integer> {

    Optional<Noticia> findByTitulo(String titulo);

    boolean existsByTitulo(String titulo);

}
