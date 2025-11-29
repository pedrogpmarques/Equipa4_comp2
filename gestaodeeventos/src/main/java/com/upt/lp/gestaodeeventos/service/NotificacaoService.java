package com.upt.lp.gestaodeeventos.service;

import com.upt.lp.gestaodeeventos.entity.Evento;
import com.upt.lp.gestaodeeventos.entity.Inscricao;
import com.upt.lp.gestaodeeventos.entity.Notificacao;
import com.upt.lp.gestaodeeventos.entity.Utilizador;
import com.upt.lp.gestaodeeventos.exception.BadRequestException;
import com.upt.lp.gestaodeeventos.exception.ResourceNotFoundException;
import com.upt.lp.gestaodeeventos.repository.EventoRepository;
import com.upt.lp.gestaodeeventos.repository.NotificacaoRepository;
import com.upt.lp.gestaodeeventos.repository.UtilizadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificacaoService {

    @Autowired
    private NotificacaoRepository notificacaoRepository;

    @Autowired
    private UtilizadorRepository utilizadorRepository;

    @Autowired
    private EventoRepository eventoRepository;

    // 🔔 Enviar notificação a UM participante
    public void enviarNotificacao(Integer idParticipante, String titulo, String mensagem) {

        Utilizador participante = utilizadorRepository.findById(idParticipante)
                .orElseThrow(() -> new ResourceNotFoundException("Participante não encontrado: " + idParticipante));

        Notificacao n = new Notificacao();
        n.setTitulo(titulo);
        n.setMensagem(mensagem);
        n.setEstado(Notificacao.EstadoNotificacao.NAO_LIDA);
        n.setDataEnvio(LocalDateTime.now());
        n.setParticipante(participante);

        notificacaoRepository.save(n);
    }

    // 🔔 Enviar notificação a TODOS os participantes inscritos num evento
    public void enviarNotificacaoParaParticipantesDoEvento(Integer idEvento, String titulo, String mensagem) {

        Evento evento = eventoRepository.findById(idEvento)
                .orElseThrow(() -> new ResourceNotFoundException("Evento não encontrado: " + idEvento));

        List<Inscricao> inscricoes = evento.getInscricoes();

        if (inscricoes == null || inscricoes.isEmpty()) {
            throw new BadRequestException("O evento não tem participantes inscritos.");
        }

        for (Inscricao insc : inscricoes) {
            Utilizador participante = insc.getParticipante();
            if (participante != null) {
                enviarNotificacao(participante.getId(), titulo, mensagem);
            }
        }
    }

    
    public void marcarTodasComoLidas(Integer participanteId) {

       
        List<Notificacao> notificacoes = notificacaoRepository.findByParticipanteId(participanteId);

        if (notificacoes.isEmpty()) {
            return; 
        }

    
        for (Notificacao n : notificacoes) {
            n.setEstado(Notificacao.EstadoNotificacao.LIDA);
        }

     
        notificacaoRepository.saveAll(notificacoes);
    } 
    
  
    public List<Notificacao> listarNotificacoes(Integer idParticipante) {

        Utilizador participante = utilizadorRepository.findById(idParticipante)
                .orElseThrow(() -> new ResourceNotFoundException("Participante não encontrado: " + idParticipante));

        return participante.getNotificacoes();
    }

    // 🔔 Marcar notificação como lida
    public Notificacao marcarComoLida(Integer idNotificacao, Integer idParticipante) {

        Notificacao n = notificacaoRepository.findById(idNotificacao)
                .orElseThrow(() -> new ResourceNotFoundException("Notificação não encontrada: " + idNotificacao));

        if (!n.getParticipante().getId().equals(idParticipante)) {
            throw new BadRequestException("Esta notificação não pertence ao participante indicado.");
        }

        n.setEstado(Notificacao.EstadoNotificacao.LIDA);

        return notificacaoRepository.save(n);
    }
}
