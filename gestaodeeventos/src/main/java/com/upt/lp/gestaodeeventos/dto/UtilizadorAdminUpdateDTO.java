package com.upt.lp.gestaodeeventos.dto;

public class UtilizadorAdminUpdateDTO {

    private String estadoUtilizador; // "ATIVO" / "INATIVO"
    private String tipoUtilizador;   // "ADMIN" / "ORGANIZADOR" / "PARTICIPANTE"

    public UtilizadorAdminUpdateDTO() {}

    public String getEstadoUtilizador() {
        return estadoUtilizador;
    }

    public void setEstadoUtilizador(String estadoUtilizador) {
        this.estadoUtilizador = estadoUtilizador;
    }

    public String getTipoUtilizador() {
        return tipoUtilizador;
    }

    public void setTipoUtilizador(String tipoUtilizador) {
        this.tipoUtilizador = tipoUtilizador;
    }
}
