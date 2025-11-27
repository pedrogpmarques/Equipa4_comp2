package com.upt.lp.gestaodeeventos.dto;

import com.upt.lp.gestaodeeventos.entity.Utilizador;

public class UtilizadorDTO {

    private Integer id;
    private String nomeUtilizador;
    private String email;
    private String tipoUtilizador;
    private String estadoUtilizador;

    public UtilizadorDTO() {
    }

    public UtilizadorDTO(Utilizador u) {
        this.id = u.getId();
        this.nomeUtilizador = u.getNomeUtilizador();
        this.email = u.getEmail();
        this.tipoUtilizador = u.getTipoUtilizador().name();
        this.estadoUtilizador = u.getEstadoUtilizador().name();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeUtilizador() {
        return nomeUtilizador;
    }

    public void setNomeUtilizador(String nomeUtilizador) {
        this.nomeUtilizador = nomeUtilizador;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipoUtilizador() {
        return tipoUtilizador;
    }

    public void setTipoUtilizador(String tipoUtilizador) {
        this.tipoUtilizador = tipoUtilizador;
    }

    public String getEstadoUtilizador() {
        return estadoUtilizador;
    }

    public void setEstadoUtilizador(String estadoUtilizador) {
        this.estadoUtilizador = estadoUtilizador;
    }
}
