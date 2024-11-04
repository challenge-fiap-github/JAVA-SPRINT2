package model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "historico_pontuacao")
public class HistoricoPontuacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relacionamento com Usuario
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Temporal(TemporalType.DATE)
    private Date dataConsulta;

    private Integer pontosGanhos;

    private Integer pontosTotais;

    @Column(nullable = false, length = 20)
    private String tipo; // 'Consulta Avaliativa', 'Consulta Preventiva', 'Bônus'
}
