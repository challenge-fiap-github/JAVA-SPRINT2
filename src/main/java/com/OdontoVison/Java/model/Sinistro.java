package com.OdontoVison.Java.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "sinistro")

public class Sinistro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relacionamento com Usuario (paciente)
    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    private Usuario paciente;

    // ID do procedimento, caso ele esteja em outra tecnologia
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

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getPaciente() {
        return paciente;
    }

    public void setPaciente(Usuario paciente) {
        this.paciente = paciente;
    }

    public Long getProcedimentoId() {
        return procedimentoId;
    }

    public void setProcedimentoId(Long procedimentoId) {
        this.procedimentoId = procedimentoId;
    }

    public Date getDataSinistro() {
        return dataSinistro;
    }

    public void setDataSinistro(Date dataSinistro) {
        this.dataSinistro = dataSinistro;
    }

    public String getRiscoFraude() {
        return riscoFraude;
    }

    public void setRiscoFraude(String riscoFraude) {
        this.riscoFraude = riscoFraude;
    }

    public Double getScoreFraude() {
        return scoreFraude;
    }

    public void setScoreFraude(Double scoreFraude) {
        this.scoreFraude = scoreFraude;
    }

    public String getDescricaoRisco() {
        return descricaoRisco;
    }

    public void setDescricaoRisco(String descricaoRisco) {
        this.descricaoRisco = descricaoRisco;
    }

    // Método auxiliar para obter o ID do paciente (usuário)
    public Long getUsuarioId() {
        return this.paciente != null ? this.paciente.getId() : null;
    }

    // Métodos equals e hashCode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sinistro sinistro = (Sinistro) o;

        return id != null ? id.equals(sinistro.id) : sinistro.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    // Método toString

    @Override
    public String toString() {
        return "Sinistro{" +
                "id=" + id +
                ", paciente=" + (paciente != null ? paciente.getId() : "null") +
                ", procedimentoId=" + procedimentoId +
                ", dataSinistro=" + dataSinistro +
                ", riscoFraude='" + riscoFraude + '\'' +
                ", scoreFraude=" + scoreFraude +
                ", descricaoRisco='" + descricaoRisco + '\'' +
                '}';
    }
}
