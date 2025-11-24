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

    // Criar utilizador
    public Utilizador criarUtilizador(Utilizador u) {

        // Verificar email repetido
        if (utilizadorRepository.existsByEmail(u.getEmail())) {
            throw new BadRequestException("Já existe um utilizador com este email.");
        }

        // Forçar tipo e estado por defeito
        u.setTipoUtilizador(TipoUtilizador.PARTICIPANTE);
        u.setEstadoUtilizador(Utilizador.EstadoUtilizador.ATIVO);

        return utilizadorRepository.save(u);
    }

    // Obter utilizador por ID
    public Utilizador getById(Integer id) {
        return utilizadorRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Utilizador não encontrado: " + id)
        );
    }

    // Obter utilizador por email
    public Utilizador getByEmail(String email) {
        Utilizador u = utilizadorRepository.findByEmail(email);
        if (u == null) {
            throw new ResourceNotFoundException("Utilizador com email " + email + " não encontrado.");
        }
        return u;
    }

    // Listar todos
    public List<Utilizador> listarTodos() {
        return utilizadorRepository.findAll();
    }

    // Atualizar nome ou senha
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

    // Apagar utilizador
    public void apagarUtilizador(Integer id) {
        Utilizador u = getById(id);
        utilizadorRepository.delete(u);
    }
}
