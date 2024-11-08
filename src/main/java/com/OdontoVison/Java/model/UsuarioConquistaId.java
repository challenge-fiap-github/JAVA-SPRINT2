package com.OdontoVison.Java.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UsuarioConquistaId implements Serializable {

    @Column(name = "usuario_id")
    private Long usuarioId;

    @Column(name = "conquista_id")
    private Long conquistaId;

    // Construtor padrão
    public UsuarioConquistaId() {}

    // Construtor com argumentos
    public UsuarioConquistaId(Long usuarioId, Long conquistaId) {
        this.usuarioId = usuarioId;
        this.conquistaId = conquistaId;
    }

    // Getters e Setters
    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getConquistaId() {
        return conquistaId;
    }

    public void setConquistaId(Long conquistaId) {
        this.conquistaId = conquistaId;
    }

    // Método equals baseado nos campos 'usuarioId' e 'conquistaId'
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsuarioConquistaId that = (UsuarioConquistaId) o;

        if (usuarioId != null ? !usuarioId.equals(that.usuarioId) : that.usuarioId != null) return false;
        return conquistaId != null ? conquistaId.equals(that.conquistaId) : that.conquistaId == null;
    }

    // Método hashCode baseado nos campos 'usuarioId' e 'conquistaId'
    @Override
    public int hashCode() {
        int result = usuarioId != null ? usuarioId.hashCode() : 0;
        result = 31 * result + (conquistaId != null ? conquistaId.hashCode() : 0);
        return result;
    }

    // Método toString
    @Override
    public String toString() {
        return "UsuarioConquistaId{" +
                "usuarioId=" + usuarioId +
                ", conquistaId=" + conquistaId +
                '}';
    }
}