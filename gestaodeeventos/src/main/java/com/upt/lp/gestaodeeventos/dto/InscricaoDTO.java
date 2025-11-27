package com.upt.lp.gestaodeeventos.dto;

import com.upt.lp.gestaodeeventos.entity.Inscricao;

import java.time.LocalDateTime;

public class InscricaoDTO {

    private Integer id;
    private Integer eventoId;
    private Integer participanteId;
    private LocalDateTime dataInscricao;
    private String estadoInscricao;

    public InscricaoDTO() {
    }

    public InscricaoDTO(Inscricao i) {
        this.id = i.getId();
        this.eventoId = i.getEvento() != null ? i.getEvento().getId() : null;
        this.participanteId = i.getParticipante() != null ? i.getParticipante().getId() : null;
        this.dataInscricao = i.getDataInscricao();
        this.estadoInscricao = i.getEstadoInscricao() != null ? i.getEstadoInscricao().name() : null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEventoId() {
        return eventoId;
    }

    public void setEventoId(Integer eventoId) {
        this.eventoId = eventoId;
    }

    public Integer getParticipanteId() {
        return participanteId;
    }

    public void setParticipanteId(Integer participanteId) {
        this.participanteId = participanteId;
    }

    public LocalDateTime getDataInscricao() {
        return dataInscricao;
    }

    public void setDataInscricao(LocalDateTime dataInscricao) {
        this.dataInscricao = dataInscricao;
    }

    public String getEstadoInscricao() {
        return estadoInscricao;
    }

    public void setEstadoInscricao(String estadoInscricao) {
        this.estadoInscricao = estadoInscricao;
    }
}
