package model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "sinistro")
public class Sinistro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relacionamento com Usuario (paciente)
    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Usuario paciente;

    // Como Procedimento está em outra tecnologia, usaremos o ID
    @Column(name = "procedimento_id")
    private Long procedimentoId;

    @Temporal(TemporalType.DATE)
    private Date dataSinistro;

    @Column(nullable = false, length = 1)
    private String riscoFraude; // 'S' ou 'N'

    @Column(precision = 5, scale = 2)
    private Double scoreFraude;

    @Column(length = 255)
    private String descricaoRisco;
}