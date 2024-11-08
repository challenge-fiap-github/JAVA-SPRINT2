package com.OdontoVison.Java.dto.Sinistro;

import jakarta.validation.constraints.NotNull;
import java.util.Date;

public class SinistroRequestDTO {

    @NotNull(message = "ID do paciente é obrigatório.")
    private Long pacienteId;

    @NotNull(message = "ID do procedimento é obrigatório.")
    private Long procedimentoId;

    private Date dataSinistro;

    private String riscoFraude; // 'S' ou 'N'

    private Double scoreFraude;

    private String descricaoRisco;

    // Getters e Setters

    public @NotNull(message = "ID do paciente é obrigatório.") Long getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(@NotNull(message = "ID do paciente é obrigatório.") Long pacienteId) {
        this.pacienteId = pacienteId;
    }

    public @NotNull(message = "ID do procedimento é obrigatório.") Long getProcedimentoId() {
        return procedimentoId;
    }

    public void setProcedimentoId(@NotNull(message = "ID do procedimento é obrigatório.") Long procedimentoId) {
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
}
