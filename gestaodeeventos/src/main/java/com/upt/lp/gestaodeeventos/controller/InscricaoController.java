package com.upt.lp.gestaodeeventos.controller;

import com.upt.lp.gestaodeeventos.dto.InscricaoCreateDTO;
import com.upt.lp.gestaodeeventos.dto.InscricaoDTO;
import com.upt.lp.gestaodeeventos.entity.Inscricao;
import com.upt.lp.gestaodeeventos.service.InscricaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/inscricoes")
public class InscricaoController {

    @Autowired
    private InscricaoService inscricaoService;

    
    @PostMapping
    public InscricaoDTO criar(@RequestBody InscricaoCreateDTO dto) {

        Inscricao i = inscricaoService.criarInscricao(
                dto.getEventoId(),
                dto.getParticipanteId()
        );

        return new InscricaoDTO(i);
    }

    @GetMapping
    public List<InscricaoDTO> listar() {
        return inscricaoService.listarInscricoes()
                .stream()
                .map(InscricaoDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public InscricaoDTO getById(@PathVariable Integer id) {
        return new InscricaoDTO(inscricaoService.getById(id));
    }

    @PutMapping("/{id}/cancelar")
    public void cancelar(@PathVariable Integer id,
                         @RequestParam Integer participanteId) {
        inscricaoService.cancelarInscricao(id, participanteId);
    }
}
