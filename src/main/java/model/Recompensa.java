package model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "recompensa")
public class Recompensa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String descricao;

    @Column(nullable = false)
    private Integer pontosNecessarios;

    private Integer quantidadeDisponivel;

    @Temporal(TemporalType.DATE)
    private Date dataExpiracao;
}
