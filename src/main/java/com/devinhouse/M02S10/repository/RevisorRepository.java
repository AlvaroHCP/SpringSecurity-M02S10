package com.devinhouse.M02S10.repository;

import com.devinhouse.M02S10.model.Revisor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RevisorRepository extends JpaRepository<Revisor, Integer> {

    Optional<Revisor> findByNome(String Nome);

    boolean existsByNome(String Nome);

}
