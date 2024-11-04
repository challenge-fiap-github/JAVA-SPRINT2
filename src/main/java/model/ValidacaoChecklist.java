package model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "validacao_checklist")
public class ValidacaoChecklist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relacionamento com Usuario
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    // Como Consulta está em outra tecnologia, usamos o ID
    @Column(name = "consulta_id", nullable = false)
    private Long consultaId;

    @Temporal(TemporalType.DATE)
    private Date dataValidacao;

    @Column(length = 1)
    private String statusValidacao; // 'S' ou 'N'

    private Integer pontosBonus;

    @PrePersist
    public void prePersist() {
        dataValidacao = new Date();
    }
}
