package com.upt.lp.gestaodeeventos.dto;

public class InscricaoCreateDTO {

    private Integer eventoId;
    private Integer participanteId;

    public InscricaoCreateDTO() {
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
}
