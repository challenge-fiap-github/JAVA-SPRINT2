package model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class UsuarioNivelId implements Serializable {

    @Column(name = "usuario_id")
    private Long usuarioId;

    @Column(name = "nivel_id")
    private Long nivelId;

}
