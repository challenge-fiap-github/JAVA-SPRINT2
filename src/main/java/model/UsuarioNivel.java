package model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "usuario_nivel")
public class UsuarioNivel {

    @EmbeddedId
    private UsuarioNivelId id;

    @ManyToOne
    @MapsId("usuarioId")
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @MapsId("nivelId")
    @JoinColumn(name = "nivel_id")
    private Nivel nivel;

    @Column(nullable = false)
    private Integer pontosAtuais;

    @Temporal(TemporalType.DATE)
    private Date dataUltimaAtualizacao;

    @PrePersist
    public void prePersist() {
        dataUltimaAtualizacao = new Date();
    }
}
