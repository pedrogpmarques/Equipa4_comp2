package com.upt.lp.gestaodeeventos.controller;

import com.upt.lp.gestaodeeventos.dto.EventoCreateDTO;
import com.upt.lp.gestaodeeventos.dto.EventoDTO;
import com.upt.lp.gestaodeeventos.dto.AvaliacaoDTO;
import com.upt.lp.gestaodeeventos.entity.Evento;
import com.upt.lp.gestaodeeventos.entity.Avaliacao;
import com.upt.lp.gestaodeeventos.exception.BadRequestException;
import com.upt.lp.gestaodeeventos.service.EventoService;
import com.upt.lp.gestaodeeventos.service.AvaliacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/eventos")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @Autowired
    private AvaliacaoService avaliacaoService;

    @PostMapping
    public EventoDTO criar(@RequestParam Integer organizadorId,
                           @RequestBody EventoCreateDTO dto) {

        Evento dados = new Evento();
        dados.setTitulo(dto.getTitulo());
        dados.setDescricao(dto.getDescricao());
        dados.setCategoria(dto.getCategoria());
        dados.setLocal(dto.getLocal());
        dados.setDataInicio(dto.getDataInicio());
        dados.setDataFim(dto.getDataFim());
        dados.setCapacidade(dto.getCapacidade());

        Evento criado = eventoService.criarEvento(dados, organizadorId);
        return new EventoDTO(criado);
    }

    @GetMapping
    public List<EventoDTO> listar(
            @RequestParam(required = false) String estado,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime inicio,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime fim) {

        if (estado == null && inicio == null && fim == null) {
            return eventoService.listarEventos().stream()
                    .map(EventoDTO::new)
                    .collect(Collectors.toList());
        }

        Evento.EstadoEvento estadoEnum = null;

        if (estado != null) {
            try {
                estadoEnum = Evento.EstadoEvento.valueOf(estado.toUpperCase());
            } catch (IllegalArgumentException ex) {
                throw new BadRequestException("Estado inválido: " + estado);
            }
        }

        return eventoService.filtrarEventos(estadoEnum, inicio, fim).stream()
                .map(EventoDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/disponiveis")
    public List<EventoDTO> listarDisponiveis() {
        return eventoService.listarEventosDisponiveis().stream()
                .map(EventoDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public EventoDTO getById(@PathVariable Integer id) {
        return new EventoDTO(eventoService.getById(id));
    }

    @PutMapping("/{id}")
    public EventoDTO atualizar(@PathVariable Integer id,
                               @RequestParam Integer organizadorId,
                               @RequestBody EventoCreateDTO dto) {

        Evento dados = new Evento();
        dados.setTitulo(dto.getTitulo());
        dados.setDescricao(dto.getDescricao());
        dados.setCategoria(dto.getCategoria());
        dados.setLocal(dto.getLocal());
        dados.setDataInicio(dto.getDataInicio());
        dados.setDataFim(dto.getDataFim());
        dados.setCapacidade(dto.getCapacidade());

        Evento atualizado = eventoService.atualizarEvento(id, dados, organizadorId);
        return new EventoDTO(atualizado);
    }

    @PutMapping("/{id}/ativar")
    public EventoDTO ativarEvento(@PathVariable Integer id,
                                  @RequestParam Integer organizadorId) {

        Evento evento = eventoService.ativarEvento(id, organizadorId);
        return new EventoDTO(evento);
    }

    @PutMapping("/{id}/cancelar")
    public void cancelarEvento(@PathVariable Integer id) {
        eventoService.cancelarEvento(id);
    }


    @GetMapping("/{id}/avaliacoes")
    public List<AvaliacaoDTO> listarAvaliacoesDoEvento(@PathVariable Integer id) {
        return avaliacaoService.listarPorEvento(id)
                .stream()
                .map(AvaliacaoDTO::new)
                .collect(Collectors.toList());
    }


    @GetMapping("/{id}/avaliacoes/media")
    public Map<String, Object> obterMediaAvaliacoes(@PathVariable Integer id) {

        var avaliacoes = avaliacaoService.listarPorEvento(id);

        double media = avaliacoes.stream()
                .mapToInt(Avaliacao::getPontuacao)
                .average()
                .orElse(0.0);

        Map<String, Object> resposta = new HashMap<>();
        resposta.put("eventoId", id);
        resposta.put("mediaClassificacao", media);
        resposta.put("totalAvaliacoes", avaliacoes.size());

        return resposta;
    }

}
