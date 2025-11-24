package com.upt.lp.gestaodeeventos.service;

import com.upt.lp.gestaodeeventos.entity.Notificacao;
import com.upt.lp.gestaodeeventos.entity.Utilizador;
import com.upt.lp.gestaodeeventos.exception.BadRequestException;
import com.upt.lp.gestaodeeventos.exception.ResourceNotFoundException;
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


    // 🔔 Enviar notificação automática
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


    // 🔔 Listar notificações de um participante
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
