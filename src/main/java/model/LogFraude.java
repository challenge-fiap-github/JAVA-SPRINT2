package model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp; // Import correto

@Data
@Entity
@Table(name = "log_fraude")
public class LogFraude {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relacionamento com Usuario (paciente)
    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    private Usuario paciente;

    @Column(nullable = false)
    private Timestamp dataAnalise;

    @Column(length = 100)
    private String tipoSuspeita;

    @Column(length = 255)
    private String descricao;

    @Column(length = 1)
    private String riscoFraude; // 'S' ou 'N'

    @Column(precision = 5, scale = 2)
    private Double scoreFraude;

    @PrePersist
    public void prePersist() {
        dataAnalise = new Timestamp(System.currentTimeMillis());
    }
}