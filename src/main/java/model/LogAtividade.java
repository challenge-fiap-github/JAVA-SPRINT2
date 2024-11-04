package model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.hateoas.Link;

import java.security.Timestamp;

@Data
@Entity
@Table(name = "log_atividade")
public class LogAtividade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relacionamento com Usuario
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(nullable = false, length = 255)
    private String descricao;

    @Column(nullable = false)
    private Timestamp dataHora;

    @PrePersist
    public void prePersist() {
        dataHora = new Timestamp(System.currentTimeMillis());
    }

}
