package com.devinhouse.M02S10.repository;

import com.devinhouse.M02S10.model.Jornalista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JornalistaRepository extends JpaRepository<Jornalista, Integer> {

    Optional<Jornalista> findByNome(String Nome);

    boolean existsByNome(String Nome);

}
