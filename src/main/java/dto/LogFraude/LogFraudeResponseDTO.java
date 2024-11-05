package dto.LogFraude;

import java.sql.Timestamp;

public class LogFraudeResponseDTO {

    private Long id;
    private Long pacienteId;
    private Timestamp dataAnalise;
    private String tipoSuspeita;
    private String descricao;
    private String riscoFraude;
    private Double scoreFraude;

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
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
}

