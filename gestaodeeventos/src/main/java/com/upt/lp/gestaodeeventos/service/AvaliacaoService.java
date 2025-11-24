package com.upt.lp.gestaodeeventos.service;

import com.upt.lp.gestaodeeventos.entity.Avaliacao;
import com.upt.lp.gestaodeeventos.entity.Evento;
import com.upt.lp.gestaodeeventos.entity.Utilizador;
import com.upt.lp.gestaodeeventos.exception.BadRequestException;
import com.upt.lp.gestaodeeventos.exception.ResourceNotFoundException;
import com.upt.lp.gestaodeeventos.repository.AvaliacaoRepository;
import com.upt.lp.gestaodeeventos.repository.EventoRepository;
import com.upt.lp.gestaodeeventos.repository.UtilizadorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private UtilizadorRepository utilizadorRepository;

    @Autowired
    private NotificacaoService notificacaoService;


    // Criar avaliação
    public Avaliacao criarAvaliacao(Integer idEvento, Integer idParticipante, Avaliacao dados) {

        Evento evento = eventoRepository.findById(idEvento)
                .orElseThrow(() -> new ResourceNotFoundException("Evento não encontrado: " + idEvento));

        Utilizador participante = utilizadorRepository.findById(idParticipante)
                .orElseThrow(() -> new ResourceNotFoundException("Participante não encontrado: " + idParticipante));

        // Validar tipo
        if (participante.getTipoUtilizador() != Utilizador.TipoUtilizador.PARTICIPANTE) {
            throw new BadRequestException("Apenas PARTICIPANTES podem avaliar eventos.");
        }

        // Impedir que o organizador avalie o próprio evento
        if (evento.getOrganizador().getId().equals(idParticipante)) {
            throw new BadRequestException("O organizador não pode avaliar o próprio evento.");
        }

        // Validar se está inscrito
        boolean inscrito = evento.getInscricoes().stream()
                .anyMatch(i -> i.getParticipante().getId().equals(idParticipante));

        if (!inscrito) {
            throw new BadRequestException("O participante precisa estar inscrito para avaliar o evento.");
        }

        // Verificar se o evento já terminou
        if (evento.getDataFim().isAfter(LocalDateTime.now())) {
            throw new BadRequestException("Só é possível avaliar eventos já terminados.");
        }

        // Verificar se já avaliou
        boolean jaAvaliou = evento.getAvaliacoes().stream()
                .anyMatch(a -> a.getParticipante().getId().equals(idParticipante));

        if (jaAvaliou) {
            throw new BadRequestException("Já existe uma avaliação deste participante para este evento.");
        }

        // Validar pontuação
        if (dados.getPontuacao() == null || dados.getPontuacao() < 1 || dados.getPontuacao() > 5) {
            throw new BadRequestException("A pontuação deve ser entre 1 e 5.");
        }

        // Criar avaliação
        Avaliacao nova = new Avaliacao();
        nova.setEvento(evento);
        nova.setParticipante(participante);
        nova.setPontuacao(dados.getPontuacao());
        nova.setComentario(dados.getComentario());
        nova.setDataAvaliacao(LocalDateTime.now());

        Avaliacao avaliada = avaliacaoRepository.save(nova);

        // Notificação automática
        notificacaoService.enviarNotificacao(
                idParticipante,
                "Avaliação registada",
                "A tua avaliação do evento '" + evento.getTitulo() + "' foi submetida com sucesso."
        );

        return avaliada;
    }


    public List<Avaliacao> listarAvaliacao() {
        return avaliacaoRepository.findAll();
    }

    public Avaliacao getById(Integer id) {
        return avaliacaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Avaliação não encontrada: " + id));
    }
}
