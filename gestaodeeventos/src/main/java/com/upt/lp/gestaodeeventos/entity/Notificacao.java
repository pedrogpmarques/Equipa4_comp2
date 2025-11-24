package com.upt.lp.gestaodeeventos.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "notificacao")
public class Notificacao {

    public enum EstadoNotificacao {
        NAO_LIDA, LIDA
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_notificacao")
    private Integer id;

    @Column(name = "titulo", nullable = false, length = 100)
    private String titulo;

    @Column(name = "mensagem", nullable = false, columnDefinition = "TEXT")
    private String mensagem;

    @ManyToOne
    @JoinColumn(name = "id_participante", nullable = false)
    private Utilizador participante;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_notificacao", nullable = false, length = 20)
    private EstadoNotificacao estado = EstadoNotificacao.NAO_LIDA;

    @Column(name = "data_envio", nullable = false)
    private LocalDateTime dataEnvio = LocalDateTime.now();

    public Notificacao() {
    }

    // Getters e Setters
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

    public Utilizador getParticipante() {
        return participante;
    }

    public void setParticipante(Utilizador participante) {
        this.participante = participante;
    }

    public EstadoNotificacao getEstado() {
        return estado;
    }

    public void setEstado(EstadoNotificacao estado) {
        this.estado = estado;
    }

    public LocalDateTime getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(LocalDateTime dataEnvio) {
        this.dataEnvio = dataEnvio;
    }
}
