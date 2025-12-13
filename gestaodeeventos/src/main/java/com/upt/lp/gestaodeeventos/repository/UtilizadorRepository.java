package com.upt.lp.gestaodeeventos.repository;

import com.upt.lp.gestaodeeventos.entity.Utilizador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilizadorRepository extends JpaRepository<Utilizador, Integer> {

    boolean existsByEmail(String email);

    Optional<Utilizador> findByEmail(String email);
}
