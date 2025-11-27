package com.upt.lp.gestaodeeventos.service;

import com.upt.lp.gestaodeeventos.entity.Utilizador;
import com.upt.lp.gestaodeeventos.entity.Utilizador.TipoUtilizador;
import com.upt.lp.gestaodeeventos.exception.BadRequestException;
import com.upt.lp.gestaodeeventos.exception.ResourceNotFoundException;
import com.upt.lp.gestaodeeventos.repository.UtilizadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilizadorService {

    @Autowired
    private UtilizadorRepository utilizadorRepository;

    public Utilizador criarUtilizador(Utilizador u) {

        if (utilizadorRepository.existsByEmail(u.getEmail())) {
            throw new BadRequestException("Já existe um utilizador com este email.");
        }

        u.setTipoUtilizador(TipoUtilizador.PARTICIPANTE);
        u.setEstadoUtilizador(Utilizador.EstadoUtilizador.ATIVO);

        return utilizadorRepository.save(u);
    }

    public Utilizador getById(Integer id) {
        return utilizadorRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Utilizador não encontrado: " + id)
        );
    }

    public Utilizador getByEmail(String email) {
        Utilizador u = utilizadorRepository.findByEmail(email);
        if (u == null) {
            throw new ResourceNotFoundException("Utilizador com email " + email + " não encontrado.");
        }
        return u;
    }

    public List<Utilizador> listarTodos() {
        return utilizadorRepository.findAll();
    }

    public Utilizador atualizarUtilizador(Integer id, Utilizador dados) {
        Utilizador existente = getById(id);

        if (dados.getNomeUtilizador() != null) {
            existente.setNomeUtilizador(dados.getNomeUtilizador());
        }

        if (dados.getSenhaUtilizador() != null) {
            existente.setSenhaUtilizador(dados.getSenhaUtilizador());
        }

        return utilizadorRepository.save(existente);
    }

    public void apagarUtilizador(Integer id) {
        Utilizador u = getById(id);
        utilizadorRepository.delete(u);
    }
}
