package com.upt.lp.gestaodeeventos.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "utilizador")
public class Utilizador {

    public enum TipoUtilizador { ADMIN, ORGANIZADOR, PARTICIPANTE }
    public enum EstadoUtilizador { ATIVO, INATIVO }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_utilizador")
    private Integer id;

    @Column(name = "nome_utilizador", nullable = false, length = 100)
    private String nomeUtilizador;

    @Column(name = "email", nullable = false, unique = true, length = 255)
    private String email;

    @Column(name = "senha_utilizador", nullable = false, length = 255)
    @JsonIgnore // nunca queremos devolver a senha na API
    private String senhaUtilizador;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_utilizador", nullable = false, length = 20)
    private TipoUtilizador tipoUtilizador = TipoUtilizador.PARTICIPANTE;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_utilizador", nullable = false, length = 20)
    private EstadoUtilizador estadoUtilizador = EstadoUtilizador.ATIVO;

    @OneToMany(mappedBy = "organizador")
    @JsonIgnore
    private List<Evento> eventosOrganizados = new ArrayList<>();

    @OneToMany(mappedBy = "participante")
    @JsonIgnore
    private List<Inscricao> inscricoes = new ArrayList<>();

    @OneToMany(mappedBy = "participante")
    @JsonIgnore
    private List<Avaliacao> avaliacoes = new ArrayList<>();

    @OneToMany(mappedBy = "participante")
    @JsonIgnore
    private List<Notificacao> notificacoes = new ArrayList<>();

    public Utilizador() {
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

    public String getSenhaUtilizador() {
        return senhaUtilizador;
    }

    public void setSenhaUtilizador(String senhaUtilizador) {
        this.senhaUtilizador = senhaUtilizador;
    }

    public TipoUtilizador getTipoUtilizador() {
        return tipoUtilizador;
    }

    public void setTipoUtilizador(TipoUtilizador tipoUtilizador) {
        this.tipoUtilizador = tipoUtilizador;
    }

    public EstadoUtilizador getEstadoUtilizador() {
        return estadoUtilizador;
    }

    public void setEstadoUtilizador(EstadoUtilizador estadoUtilizador) {
        this.estadoUtilizador = estadoUtilizador;
    }

    public List<Evento> getEventosOrganizados() {
        return eventosOrganizados;
    }

    public void setEventosOrganizados(List<Evento> eventosOrganizados) {
        this.eventosOrganizados = eventosOrganizados;
    }

    public List<Inscricao> getInscricoes() {
        return inscricoes;
    }

    public void setInscricoes(List<Inscricao> inscricoes) {
        this.inscricoes = inscricoes;
    }

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<Avaliacao> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }

    public List<Notificacao> getNotificacoes() {
        return notificacoes;
    }

    public void setNotificacoes(List<Notificacao> notificacoes) {
        this.notificacoes = notificacoes;
    }
}
