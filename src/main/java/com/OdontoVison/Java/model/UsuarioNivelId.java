package com.OdontoVison.Java.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UsuarioNivelId implements Serializable {

    @Column(name = "usuario_id")
    private Long usuarioId;

    @Column(name = "nivel_id")
    private Long nivelId;

    // Construtor padrão
    public UsuarioNivelId() {}

    // Construtor com argumentos
    public UsuarioNivelId(Long usuarioId, Long nivelId) {
        this.usuarioId = usuarioId;
        this.nivelId = nivelId;
    }

    // Getters e Setters
    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getNivelId() {
        return nivelId;
    }

    public void setNivelId(Long nivelId) {
        this.nivelId = nivelId;
    }

    // Método equals baseado nos campos 'usuarioId' e 'nivelId'
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsuarioNivelId that = (UsuarioNivelId) o;

        if (usuarioId != null ? !usuarioId.equals(that.usuarioId) : that.usuarioId != null) return false;
        return nivelId != null ? nivelId.equals(that.nivelId) : that.nivelId == null;
    }

    // Método hashCode baseado nos campos 'usuarioId' e 'nivelId'
    @Override
    public int hashCode() {
        int result = usuarioId != null ? usuarioId.hashCode() : 0;
        result = 31 * result + (nivelId != null ? nivelId.hashCode() : 0);
        return result;
    }

    // Método toString
    @Override
    public String toString() {
        return "UsuarioNivelId{" +
                "usuarioId=" + usuarioId +
                ", nivelId=" + nivelId +
                '}';
    }
}