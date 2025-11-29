package com.upt.lp.gestaodeeventos.repository;

import com.upt.lp.gestaodeeventos.entity.Notificacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificacaoRepository extends JpaRepository<Notificacao, Integer> {

List<Notificacao> findByParticipanteId(Integer participanteId);
  

}