package com.upt.lp.gestaodeeventos.dto;

import com.upt.lp.gestaodeeventos.entity.Avaliacao;

import java.time.LocalDateTime;

public class AvaliacaoDTO {

    private Integer id;
    private Integer eventoId;
    private Integer participanteId;
    private Integer pontuacao;
    private String comentario;
    private LocalDateTime dataAvaliacao;

    public AvaliacaoDTO() {
    }

    public AvaliacaoDTO(Avaliacao a) {
        this.id = a.getId();
        this.eventoId = a.getEvento() != null ? a.getEvento().getId() : null;
        this.participanteId = a.getParticipante() != null ? a.getParticipante().getId() : null;
        this.pontuacao = a.getPontuacao();
        this.comentario = a.getComentario();
        this.dataAvaliacao = a.getDataAvaliacao();
    }

    public Integer getId() { return id; }
    public Integer getEventoId() { return eventoId; }
    public Integer getParticipanteId() { return participanteId; }
    public Integer getPontuacao() { return pontuacao; }
    public String getComentario() { return comentario; }
    public LocalDateTime getDataAvaliacao() { return dataAvaliacao; }

    public void setId(Integer id) { this.id = id; }
    public void setEventoId(Integer eventoId) { this.eventoId = eventoId; }
    public void setParticipanteId(Integer participanteId) { this.participanteId = participanteId; }
    public void setPontuacao(Integer pontuacao) { this.pontuacao = pontuacao; }
    public void setComentario(String comentario) { this.comentario = comentario; }
    public void setDataAvaliacao(LocalDateTime dataAvaliacao) { this.dataAvaliacao = dataAvaliacao; }
}
