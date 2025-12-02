package com.upt.lp.gestaodeeventos.repository;

import com.upt.lp.gestaodeeventos.entity.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Integer> {

    List<Avaliacao> findByEventoId(Integer eventoId);
}
