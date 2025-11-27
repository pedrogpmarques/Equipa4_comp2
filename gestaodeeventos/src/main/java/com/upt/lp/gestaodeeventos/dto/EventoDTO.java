package com.upt.lp.gestaodeeventos.dto;

import com.upt.lp.gestaodeeventos.entity.Evento;

import java.time.LocalDateTime;

public class EventoDTO {

    private Integer id;
    private String titulo;
    private String descricao;
    private String categoria;
    private String local;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private Integer capacidade;
    private String estadoEvento;
    private Integer organizadorId;

    public EventoDTO() {
    }

    public EventoDTO(Evento e) {
        this.id = e.getId();
        this.titulo = e.getTitulo();
        this.descricao = e.getDescricao();
        this.categoria = e.getCategoria();
        this.local = e.getLocal();
        this.dataInicio = e.getDataInicio();
        this.dataFim = e.getDataFim();
        this.capacidade = e.getCapacidade();
        this.estadoEvento = e.getEstadoEvento() != null ? e.getEstadoEvento().name() : null;
        this.organizadorId = (e.getOrganizador() != null) ? e.getOrganizador().getId() : null;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public LocalDateTime getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDateTime dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDateTime getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDateTime dataFim) {
        this.dataFim = dataFim;
    }

    public Integer getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(Integer capacidade) {
        this.capacidade = capacidade;
    }

    public String getEstadoEvento() {
        return estadoEvento;
    }

    public void setEstadoEvento(String estadoEvento) {
        this.estadoEvento = estadoEvento;
    }

    public Integer getOrganizadorId() {
        return organizadorId;
    }

    public void setOrganizadorId(Integer organizadorId) {
        this.organizadorId = organizadorId;
    }
}
