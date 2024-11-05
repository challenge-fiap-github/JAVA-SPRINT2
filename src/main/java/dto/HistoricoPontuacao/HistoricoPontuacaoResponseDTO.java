package dto.HistoricoPontuacao;

import java.util.Date;

public class HistoricoPontuacaoResponseDTO {

    private Long id;
    private Long usuarioId;
    private Date dataConsulta;
    private Integer pontosGanhos;
    private Integer pontosTotais;
    private String tipo;

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Date getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(Date dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public Integer getPontosGanhos() {
        return pontosGanhos;
    }

    public void setPontosGanhos(Integer pontosGanhos) {
        this.pontosGanhos = pontosGanhos;
    }

    public Integer getPontosTotais() {
        return pontosTotais;
    }

    public void setPontosTotais(Integer pontosTotais) {
        this.pontosTotais = pontosTotais;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
