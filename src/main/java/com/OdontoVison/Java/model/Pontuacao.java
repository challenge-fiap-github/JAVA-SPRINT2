package com.OdontoVison.Java.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "pontuacao")
public class Pontuacao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pontuacao_seq")
    @SequenceGenerator(name = "pontuacao_seq", sequenceName = "pontuacao_sequence", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private Integer pontos;

    @Column(nullable = false)
    private Integer pontosAcumulados;

    @Column(length = 50)
    private String tipo;

    @Temporal(TemporalType.DATE)
    private Date dataRegistro;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    // Getters e setters para os campos

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPontos() {
        return pontos;
    }

    public void setPontos(Integer pontos) {
        this.pontos = pontos;
    }

    public Integer getPontosAcumulados() {
        return pontosAcumulados;
    }

    public void setPontosAcumulados(Integer pontosAcumulados) {
        this.pontosAcumulados = pontosAcumulados;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(Date dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    // Método auxiliar para obter o ID do usuário
    public Long getUsuarioId() {
        return this.usuario != null ? this.usuario.getId() : null;
    }

    // Método equals baseado no campo 'id'
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pontuacao pontuacao = (Pontuacao) o;

        return id != null ? id.equals(pontuacao.id) : pontuacao.id == null;
    }

    // Método hashCode baseado no campo 'id'
    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    // Método toString
    @Override
    public String toString() {
        return "Pontuacao{" +
                "id=" + id +
                ", pontos=" + pontos +
                ", pontosAcumulados=" + pontosAcumulados +
                ", tipo='" + tipo + '\'' +
                ", dataRegistro=" + dataRegistro +
                ", usuario=" + (usuario != null ? usuario.getId() : "null") +
                '}';
    }
}