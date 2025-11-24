package com.upt.lp.gestaodeeventos.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "inscricao")
public class Inscricao {

    public enum EstadoInscricao {
        PENDENTE, CONFIRMADA, CANCELADA
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_inscricao")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_evento", nullable = false)
    private Evento evento;

    @ManyToOne
    @JoinColumn(name = "id_participante", nullable = false)
    private Utilizador participante;

    @Column(name = "data_inscricao", nullable = false)
    private LocalDateTime dataInscricao = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_inscricao", nullable = false, length = 20)
    private EstadoInscricao estadoInscricao = EstadoInscricao.PENDENTE;

    public Inscricao() {
    }

    // Getters e Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public Utilizador getParticipante() {
        return participante;
    }

    public void setParticipante(Utilizador participante) {
        this.participante = participante;
    }

    public LocalDateTime getDataInscricao() {
        return dataInscricao;
    }

    public void setDataInscricao(LocalDateTime dataInscricao) {
        this.dataInscricao = dataInscricao;
    }

    public EstadoInscricao getEstadoInscricao() {
        return estadoInscricao;
    }

    public void setEstadoInscricao(EstadoInscricao estadoInscricao) {
        this.estadoInscricao = estadoInscricao;
    }
}
