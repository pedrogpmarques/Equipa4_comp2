package com.upt.lp.gestaodeeventos.dto;

import com.upt.lp.gestaodeeventos.entity.Notificacao;

import java.time.LocalDateTime;

public class NotificaoDTO {

    private Integer id;
    private String titulo;
    private String mensagem;
    private String estado;
    private LocalDateTime dataEnvio;
    private Integer participanteId;

    public NotificaoDTO() {
    }

    public NotificaoDTO(Notificacao n) {
        this.id = n.getId();
        this.titulo = n.getTitulo();
        this.mensagem = n.getMensagem();
        this.estado = n.getEstado() != null ? n.getEstado().name() : null;
        this.dataEnvio = n.getDataEnvio();
        this.participanteId = n.getParticipante() != null ? n.getParticipante().getId() : null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(LocalDateTime dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    public Integer getParticipanteId() {
        return participanteId;
    }

    public void setParticipanteId(Integer participanteId) {
        this.participanteId = participanteId;
    }
}
