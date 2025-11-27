package com.upt.lp.gestaodeeventos.controller;

import com.upt.lp.gestaodeeventos.dto.EventoCreateDTO;
import com.upt.lp.gestaodeeventos.dto.EventoDTO;
import com.upt.lp.gestaodeeventos.entity.Evento;
import com.upt.lp.gestaodeeventos.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/eventos")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @PostMapping
    public EventoDTO criar(@RequestParam Integer organizadorId,
                           @RequestBody EventoCreateDTO dto) {

        Evento e = new Evento();
        e.setTitulo(dto.getTitulo());
        e.setDescricao(dto.getDescricao());
        e.setCategoria(dto.getCategoria());
        e.setLocal(dto.getLocal());
        e.setDataInicio(dto.getDataInicio());
        e.setDataFim(dto.getDataFim());
        e.setCapacidade(dto.getCapacidade());

        Evento criado = eventoService.criarEvento(e, organizadorId);
        return new EventoDTO(criado);
    }

    @GetMapping
    public List<EventoDTO> listar() {
        return eventoService.listarEventos()
                .stream()
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

    @DeleteMapping("/{id}")
    public void apagar(@PathVariable Integer id,
                       @RequestParam Integer organizadorId) {
        eventoService.apagarEvento(id, organizadorId);
    }
}
