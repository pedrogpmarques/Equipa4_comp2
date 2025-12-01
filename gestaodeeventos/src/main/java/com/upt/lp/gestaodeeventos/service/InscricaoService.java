package com.upt.lp.gestaodeeventos.service;

import com.upt.lp.gestaodeeventos.entity.Evento;
import com.upt.lp.gestaodeeventos.entity.Inscricao;
import com.upt.lp.gestaodeeventos.entity.Utilizador;
import com.upt.lp.gestaodeeventos.exception.BadRequestException;
import com.upt.lp.gestaodeeventos.exception.ResourceNotFoundException;
import com.upt.lp.gestaodeeventos.repository.EventoRepository;
import com.upt.lp.gestaodeeventos.repository.InscricaoRepository;
import com.upt.lp.gestaodeeventos.repository.UtilizadorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class InscricaoService {

    @Autowired
    private InscricaoRepository inscricaoRepository;

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private UtilizadorRepository utilizadorRepository;

    @Autowired
    private NotificacaoService notificacaoService;


    public Inscricao criarInscricao(Integer idEvento, Integer idParticipante) {

        Evento evento = eventoRepository.findById(idEvento)
                .orElseThrow(() -> new ResourceNotFoundException("Evento não encontrado: " + idEvento));
        
        if (evento.getEstadoEvento() != Evento.EstadoEvento.ATIVO) {
            throw new BadRequestException("Só é possível inscrever em eventos com estado ATIVO.");
        }
        
        

        Utilizador participante = utilizadorRepository.findById(idParticipante)
                .orElseThrow(() -> new ResourceNotFoundException("Participante não encontrado: " + idParticipante));

        if (participante.getTipoUtilizador() != Utilizador.TipoUtilizador.PARTICIPANTE) {
            throw new BadRequestException("Apenas PARTICIPANTES podem inscrever-se em eventos.");
        }

        if (evento.getCapacidade() != null && evento.getCapacidade() <= 0) {
            throw new BadRequestException("O evento está esgotado. Não há vagas disponíveis.");
        }

        boolean jaInscrito = evento.getInscricoes().stream()
                .anyMatch(i -> i.getParticipante().getId().equals(idParticipante));

        if (jaInscrito) {
            throw new BadRequestException("Este participante já está inscrito neste evento.");
        }

        Inscricao nova = new Inscricao();
        nova.setEvento(evento);
        nova.setParticipante(participante);
        nova.setEstadoInscricao(Inscricao.EstadoInscricao.CONFIRMADA);
        nova.setDataInscricao(LocalDateTime.now());

        if (evento.getCapacidade() != null) {
            evento.setCapacidade(evento.getCapacidade() - 1);
            eventoRepository.save(evento);
        }

        notificacaoService.enviarNotificacao(
                idParticipante,
                "Inscrição Confirmada",
                "Inscreveste-te no evento: " + evento.getTitulo()
        );

        return inscricaoRepository.save(nova);
    }
    
    


    public List<Inscricao> listarInscricoes() {
        return inscricaoRepository.findAll();
    }


    public Inscricao getById(Integer id) {
        return inscricaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inscrição não encontrada: " + id));
    }


    public void cancelarInscricao(Integer idInscricao, Integer idParticipante) {

        Inscricao i = getById(idInscricao);

        if (!i.getParticipante().getId().equals(idParticipante)) {
            throw new BadRequestException("Só o próprio participante pode cancelar a sua inscrição.");
        }

        Evento evento = i.getEvento();
        if (evento.getCapacidade() != null) {
            evento.setCapacidade(evento.getCapacidade() + 1);
            eventoRepository.save(evento);
        }

        i.setEstadoInscricao(Inscricao.EstadoInscricao.CANCELADA);
        inscricaoRepository.save(i);

        notificacaoService.enviarNotificacao(
                idParticipante,
                "Inscrição Cancelada",
                "Cancelaste a inscrição no evento: " + evento.getTitulo()
        );
    }
    
}
