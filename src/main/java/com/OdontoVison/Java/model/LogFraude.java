package com.OdontoVison.Java.model;

import jakarta.persistence.*;
import java.sql.Timestamp;

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

    public Timestamp getDataAnalise() {
        return dataAnalise;
    }

    public void setDataAnalise(Timestamp dataAnalise) {
        this.dataAnalise = dataAnalise;
    }

    public String getTipoSuspeita() {
        return tipoSuspeita;
    }

    public void setTipoSuspeita(String tipoSuspeita) {
        this.tipoSuspeita = tipoSuspeita;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

    // Método equals baseado no campo 'id'
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LogFraude logFraude = (LogFraude) o;

        return id != null ? id.equals(logFraude.id) : logFraude.id == null;
    }

    // Método hashCode baseado no campo 'id'
    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    // Método toString
    @Override
    public String toString() {
        return "LogFraude{" +
                "id=" + id +
                ", paciente=" + (paciente != null ? paciente.getId() : "null") +
                ", dataAnalise=" + dataAnalise +
                ", tipoSuspeita='" + tipoSuspeita + '\'' +
                ", descricao='" + descricao + '\'' +
                ", riscoFraude='" + riscoFraude + '\'' +
                ", scoreFraude=" + scoreFraude +
                '}';
    }
}
