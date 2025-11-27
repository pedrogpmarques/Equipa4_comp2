package com.upt.lp.gestaodeeventos.controller;

import com.upt.lp.gestaodeeventos.dto.NotificaoCreateDTO;
import com.upt.lp.gestaodeeventos.dto.NotificaoDTO;
import com.upt.lp.gestaodeeventos.entity.Notificacao;
import com.upt.lp.gestaodeeventos.service.NotificacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/notificacoes")
public class NotificacaoController {

    @Autowired
    private NotificacaoService notificacaoService;

    @PostMapping
    public void criar(@RequestBody NotificaoCreateDTO dto) {
        notificacaoService.enviarNotificacao(
                dto.getParticipanteId(),
                dto.getTitulo(),
                dto.getMensagem()
        );
    }

    @PostMapping("/evento/{eventoId}")
    public void notificarParticipantesEvento(@PathVariable Integer eventoId,
                                             @RequestBody NotificaoCreateDTO dto) {
        notificacaoService.enviarNotificacaoParaParticipantesDoEvento(
                eventoId,
                dto.getTitulo(),
                dto.getMensagem()
        );
    }

    @GetMapping
    public List<NotificaoDTO> listarPorParticipante(@RequestParam Integer participanteId) {

        List<Notificacao> lista = notificacaoService.listarNotificacoes(participanteId);

        return lista.stream()
                .map(NotificaoDTO::new)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}/ler")
    public NotificaoDTO marcarComoLida(@PathVariable Integer id,
                                         @RequestParam Integer participanteId) {

        Notificacao n = notificacaoService.marcarComoLida(id, participanteId);
        return new NotificaoDTO(n);
    }
}
