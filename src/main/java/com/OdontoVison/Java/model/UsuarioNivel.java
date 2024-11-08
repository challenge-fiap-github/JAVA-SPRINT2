package com.OdontoVison.Java.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "usuario_nivel")
public class UsuarioNivel {

    @EmbeddedId
    private UsuarioNivelId id;

    @ManyToOne
    @MapsId("usuarioId")
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @MapsId("nivelId")
    @JoinColumn(name = "nivel_id", nullable = false)
    private Nivel nivel;

    @Column(nullable = false)
    private Integer pontosAtuais;

    @Temporal(TemporalType.DATE)
    private Date dataUltimaAtualizacao;

    @PrePersist
    public void prePersist() {
        dataUltimaAtualizacao = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        dataUltimaAtualizacao = new Date();
    }

    // Getters e Setters
    public UsuarioNivelId getId() {
        return id;
    }

    public void setId(UsuarioNivelId id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Nivel getNivel() {
        return nivel;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }

    public Integer getPontosAtuais() {
        return pontosAtuais;
    }

    public void setPontosAtuais(Integer pontosAtuais) {
        this.pontosAtuais = pontosAtuais;
    }

    public Date getDataUltimaAtualizacao() {
        return dataUltimaAtualizacao;
    }

    public void setDataUltimaAtualizacao(Date dataUltimaAtualizacao) {
        this.dataUltimaAtualizacao = dataUltimaAtualizacao;
    }

    // Método equals baseado no campo 'id'
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsuarioNivel that = (UsuarioNivel) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    // Método hashCode baseado no campo 'id'
    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    // Método toString
    @Override
    public String toString() {
        return "UsuarioNivel{" +
                "id=" + id +
                ", usuario=" + (usuario != null ? usuario.getId() : "null") +
                ", nivel=" + (nivel != null ? nivel.getId() : "null") +
                ", pontosAtuais=" + pontosAtuais +
                ", dataUltimaAtualizacao=" + dataUltimaAtualizacao +
                '}';
    }
}