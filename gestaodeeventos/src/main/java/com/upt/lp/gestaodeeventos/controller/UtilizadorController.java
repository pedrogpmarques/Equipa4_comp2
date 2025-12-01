package com.upt.lp.gestaodeeventos.controller;

import com.upt.lp.gestaodeeventos.dto.UtilizadorCreateDTO;
import com.upt.lp.gestaodeeventos.dto.UtilizadorDTO;
import com.upt.lp.gestaodeeventos.entity.Utilizador;
import com.upt.lp.gestaodeeventos.service.UtilizadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/utilizadores")
public class UtilizadorController {

    @Autowired
    private UtilizadorService utilizadorService;

    @PostMapping
    public UtilizadorDTO criar(@RequestBody UtilizadorCreateDTO dto) {
        Utilizador u = new Utilizador();
        u.setNomeUtilizador(dto.getNomeUtilizador());
        u.setEmail(dto.getEmail());
        u.setSenhaUtilizador(dto.getSenhaUtilizador());

        Utilizador criado = utilizadorService.criarUtilizador(u);
        return new UtilizadorDTO(criado);
    }

   @GetMapping
    public List<UtilizadorDTO> listar() {
        return utilizadorService.listarTodos()
                .stream()
                .map(UtilizadorDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public UtilizadorDTO getById(@PathVariable Integer id) {
        return new UtilizadorDTO(utilizadorService.getById(id));
    }

   
    @GetMapping("/ordenado")
    public List<UtilizadorDTO> listarOrdenadoPorNome() {
        return utilizadorService.listarOrdenadoPorNome()
                .stream()
                .map(UtilizadorDTO::new)
                .collect(Collectors.toList());
    }
    
    @PutMapping("/{id}")
    public UtilizadorDTO atualizar(@PathVariable Integer id,
                                   @RequestBody UtilizadorCreateDTO dto) {

        Utilizador dados = new Utilizador();
        dados.setNomeUtilizador(dto.getNomeUtilizador());
        dados.setSenhaUtilizador(dto.getSenhaUtilizador());

        Utilizador atualizado = utilizadorService.atualizarUtilizador(id, dados);
        return new UtilizadorDTO(atualizado);
    }

    @PutMapping("/{id}/inativar")
    public void inativarUtilizador(@PathVariable Integer id) {
        utilizadorService.inativarUtilizador(id);
    }

}
