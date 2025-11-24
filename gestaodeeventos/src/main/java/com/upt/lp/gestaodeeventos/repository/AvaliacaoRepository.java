package com.upt.lp.gestaodeeventos.repository;

import com.upt.lp.gestaodeeventos.entity.Avaliacao;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Integer> {
}