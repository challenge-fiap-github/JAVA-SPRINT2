package model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "pontuacao")
public class Pontuacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relacionamento com Usuario
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(nullable = false)
    private Integer pontos;

    @Column(nullable = false)
    private Integer pontosAcumulados;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date dataRegistro;

    @Temporal(TemporalType.DATE)
    private Date cicloInicial;

    @Temporal(TemporalType.DATE)
    private Date cicloFinal;

    @Column(nullable = false, length = 20)
    private String tipo; // 'Consulta Preventiva' ou 'Bônus'

    @Column(length = 1)
    private String validacaoHabitos; // 'S' ou 'N'
}
