package dto.Sinistro;

import java.util.Date;

public class SinistroResponseDTO {

    private Long id;
    private Long pacienteId;
    private Long procedimentoId;
    private Date dataSinistro;
    private String riscoFraude;
    private Double scoreFraude;
    private String descricaoRisco;

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
}