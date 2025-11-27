package com.upt.lp.gestaodeeventos.controller;

import com.upt.lp.gestaodeeventos.dto.AvaliacaoCreateDTO;
import com.upt.lp.gestaodeeventos.dto.AvaliacaoDTO;
import com.upt.lp.gestaodeeventos.entity.Avaliacao;
import com.upt.lp.gestaodeeventos.service.AvaliacaoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/avaliacoes")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoService avaliacaoService;

   
    @PostMapping
    public AvaliacaoDTO criar(@RequestBody AvaliacaoCreateDTO dto) {

        Avaliacao a = new Avaliacao();
        a.setPontuacao(dto.getPontuacao());
        a.setComentario(dto.getComentario());

        Avaliacao criada = avaliacaoService.criarAvaliacao(
                dto.getEventoId(),
                dto.getParticipanteId(),
                a
        );

        return new AvaliacaoDTO(criada);
    }

    
    @GetMapping
    public List<AvaliacaoDTO> listar() {
        return avaliacaoService.listarAvaliacao()
                .stream()
                .map(AvaliacaoDTO::new)
                .collect(Collectors.toList());
    }

    
    @GetMapping("/{id}")
    public AvaliacaoDTO getById(@PathVariable Integer id) {
        return new AvaliacaoDTO(avaliacaoService.getById(id));
    }
}
