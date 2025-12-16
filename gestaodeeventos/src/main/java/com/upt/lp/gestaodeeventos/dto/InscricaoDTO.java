package com.upt.lp.gestaodeeventos.dto;

import com.upt.lp.gestaodeeventos.entity.Inscricao;

import java.time.LocalDateTime;

public class InscricaoDTO {

    private Integer id;

    // mantém IDs (úteis para regras e operações)
    private Integer eventoId;
    private Integer participanteId;

    // novos campos para UI
    private String eventoTitulo;
    private String participanteNome;

    private LocalDateTime dataInscricao;
    private String estadoInscricao;

    public InscricaoDTO() {}

    public InscricaoDTO(Inscricao i) {
        this.id = i.getId();

        if (i.getEvento() != null) {
            this.eventoId = i.getEvento().getId();
            this.eventoTitulo = i.getEvento().getTitulo(); // <---
        }

        if (i.getParticipante() != null) {
            this.participanteId = i.getParticipante().getId();
            this.participanteNome = i.getParticipante().getNomeUtilizador(); // <---
        }

        this.dataInscricao = i.getDataInscricao();
        this.estadoInscricao = (i.getEstadoInscricao() != null) ? i.getEstadoInscricao().name() : null;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getEventoId() { return eventoId; }
    public void setEventoId(Integer eventoId) { this.eventoId = eventoId; }

    public Integer getParticipanteId() { return participanteId; }
    public void setParticipanteId(Integer participanteId) { this.participanteId = participanteId; }

    public String getEventoTitulo() { return eventoTitulo; }
    public void setEventoTitulo(String eventoTitulo) { this.eventoTitulo = eventoTitulo; }

    public String getParticipanteNome() { return participanteNome; }
    public void setParticipanteNome(String participanteNome) { this.participanteNome = participanteNome; }

    public LocalDateTime getDataInscricao() { return dataInscricao; }
    public void setDataInscricao(LocalDateTime dataInscricao) { this.dataInscricao = dataInscricao; }

    public String getEstadoInscricao() { return estadoInscricao; }
    public void setEstadoInscricao(String estadoInscricao) { this.estadoInscricao = estadoInscricao; }
}
