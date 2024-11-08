package com.OdontoVison.Java.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "usuario_conquista")
public class UsuarioConquista {

    @EmbeddedId
    private UsuarioConquistaId id;

    @ManyToOne
    @MapsId("usuarioId")
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @MapsId("conquistaId")
    @JoinColumn(name = "conquista_id")
    private Conquista conquista;

    @Temporal(TemporalType.DATE)
    private Date dataObtencao;

    @PrePersist
    public void prePersist() {
        dataObtencao = new Date();
    }

    // Getters e Setters
    public UsuarioConquistaId getId() {
        return id;
    }

    public void setId(UsuarioConquistaId id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Conquista getConquista() {
        return conquista;
    }

    public void setConquista(Conquista conquista) {
        this.conquista = conquista;
    }

    public Date getDataObtencao() {
        return dataObtencao;
    }

    public void setDataObtencao(Date dataObtencao) {
        this.dataObtencao = dataObtencao;
    }

    // Método equals baseado no campo 'id'
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsuarioConquista that = (UsuarioConquista) o;

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
        return "UsuarioConquista{" +
                "id=" + id +
                ", usuario=" + (usuario != null ? usuario.getId() : "null") +
                ", conquista=" + (conquista != null ? conquista.getId() : "null") +
                ", dataObtencao=" + dataObtencao +
                '}';
    }
}

