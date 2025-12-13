package com.upt.lp.gestaodeeventos.service;

import com.upt.lp.gestaodeeventos.entity.Utilizador;
import com.upt.lp.gestaodeeventos.entity.Utilizador.TipoUtilizador;
import com.upt.lp.gestaodeeventos.exception.BadRequestException;
import com.upt.lp.gestaodeeventos.exception.ResourceNotFoundException;
import com.upt.lp.gestaodeeventos.repository.UtilizadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;
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
        return utilizadorRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Utilizador com email " + email + " não encontrado.")
                );
    }


    public List<Utilizador> listarOrdenadoPorNome() {
        return utilizadorRepository.findAll(Sort.by("nomeUtilizador").ascending());
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

    public void inativarUtilizador(Integer id) {
        Utilizador u = utilizadorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Utilizador não encontrado."));

        u.setEstadoUtilizador(Utilizador.EstadoUtilizador.INATIVO);
        utilizadorRepository.save(u);
    }
    public Utilizador autenticar(String email, String senha) {
        Utilizador u = utilizadorRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("EMAIL_NAO_EXISTE"));

        if (u.getEstadoUtilizador() != null && u.getEstadoUtilizador().toString().equalsIgnoreCase("INATIVO")) {
            throw new RuntimeException("CONTA_INATIVA");
        }


        if (u.getSenhaUtilizador() == null || !u.getSenhaUtilizador().equals(senha)) {
            throw new RuntimeException("PASSWORD_INVALIDA");
        }

        return u;
    }



}
