package model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class UsuarioConquistaId implements Serializable {

    @Column(name = "usuario_id")
    private Long usuarioId;

    @Column(name = "conquista_id")
    private Long conquistaId;

}
