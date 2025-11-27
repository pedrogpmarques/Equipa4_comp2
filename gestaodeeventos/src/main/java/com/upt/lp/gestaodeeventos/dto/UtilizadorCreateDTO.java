package com.upt.lp.gestaodeeventos.dto;

public class UtilizadorCreateDTO {

    private String nomeUtilizador;
    private String email;
    private String senhaUtilizador;

    public UtilizadorCreateDTO() {
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

    public String getSenhaUtilizador() {
        return senhaUtilizador;
    }

    public void setSenhaUtilizador(String senhaUtilizador) {
        this.senhaUtilizador = senhaUtilizador;
    }
}
