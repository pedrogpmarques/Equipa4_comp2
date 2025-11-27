package com.upt.lp.gestaodeeventos.service;

import com.upt.lp.gestaodeeventos.entity.Evento;
import com.upt.lp.gestaodeeventos.entity.Utilizador;
import com.upt.lp.gestaodeeventos.exception.BadRequestException;
import com.upt.lp.gestaodeeventos.exception.ResourceNotFoundException;
import com.upt.lp.gestaodeeventos.repository.EventoRepository;
import com.upt.lp.gestaodeeventos.repository.UtilizadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private UtilizadorRepository utilizadorRepository;

    public Evento criarEvento(Evento e, Integer idOrganizador) {

        Utilizador organizador = utilizadorRepository.findById(idOrganizador)
                .orElseThrow(() -> new ResourceNotFoundException("Organizador não encontrado: " + idOrganizador));

        if (organizador.getTipoUtilizador() != Utilizador.TipoUtilizador.ORGANIZADOR) {
            throw new BadRequestException("Apenas utilizadores do tipo ORGANIZADOR podem criar eventos.");
        }

        if (e.getDataInicio() != null && e.getDataFim() != null) {
            if (e.getDataFim().isBefore(e.getDataInicio())) {
                throw new BadRequestException("A data de fim do evento não pode ser anterior à data de início.");
            }
        }

        if (e.getCapacidade() != null && e.getCapacidade() < 1) {
            throw new BadRequestException("A capacidade do evento deve ser maior que 0.");
        }

        e.setOrganizador(organizador);

        return eventoRepository.save(e);
    }

    public Evento getById(Integer id) {
        return eventoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evento não encontrado: " + id));
    }

    public List<Evento> listarEventos() {
        return eventoRepository.findAll();
    }

    public Evento atualizarEvento(Integer idEvento, Evento dados, Integer idOrganizador) {

        Evento existente = getById(idEvento);

        if (!existente.getOrganizador().getId().equals(idOrganizador)) {
            throw new BadRequestException("Apenas o organizador original pode atualizar este evento.");
        }

        if (dados.getTitulo() != null) existente.setTitulo(dados.getTitulo());
        if (dados.getDescricao() != null) existente.setDescricao(dados.getDescricao());
        if (dados.getCategoria() != null) existente.setCategoria(dados.getCategoria());
        if (dados.getLocal() != null) existente.setLocal(dados.getLocal());

        if (dados.getDataInicio() != null && dados.getDataFim() != null) {
            if (dados.getDataFim().isBefore(dados.getDataInicio())) {
                throw new BadRequestException("A data de fim não pode ser anterior à data de início.");
            }
            existente.setDataInicio(dados.getDataInicio());
            existente.setDataFim(dados.getDataFim());
        }

        if (dados.getCapacidade() != null) {
            if (dados.getCapacidade() < 1) {
                throw new BadRequestException("A capacidade do evento deve ser maior que 0.");
            }
            existente.setCapacidade(dados.getCapacidade());
        }

        if (dados.getEstadoEvento() != null) {
            existente.setEstadoEvento(dados.getEstadoEvento());
        }

        return eventoRepository.save(existente);
    }

    public void apagarEvento(Integer idEvento, Integer idOrganizador) {

        Evento evento = getById(idEvento);

        if (!evento.getOrganizador().getId().equals(idOrganizador)) {
            throw new BadRequestException("Apenas o organizador original pode apagar este evento.");
        }

        eventoRepository.delete(evento);
    }
}
