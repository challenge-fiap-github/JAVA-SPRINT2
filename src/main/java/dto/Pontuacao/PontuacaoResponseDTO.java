package dto.Pontuacao;

import java.util.Date;

public class PontuacaoResponseDTO {

    private Long id;
    private Integer pontos;
    private Integer pontosAcumulados;
    private Date dataRegistro;
    private Date cicloInicial;
    private Date cicloFinal;
    private String tipo;
    private String validacaoHabitos;

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPontos() {
        return pontos;
    }

    public void setPontos(Integer pontos) {
        this.pontos = pontos;
    }

    public Integer getPontosAcumulados() {
        return pontosAcumulados;
    }

    public void setPontosAcumulados(Integer pontosAcumulados) {
        this.pontosAcumulados = pontosAcumulados;
    }

    public Date getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(Date dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public Date getCicloInicial() {
        return cicloInicial;
    }

    public void setCicloInicial(Date cicloInicial) {
        this.cicloInicial = cicloInicial;
    }

    public Date getCicloFinal() {
        return cicloFinal;
    }

    public void setCicloFinal(Date cicloFinal) {
        this.cicloFinal = cicloFinal;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getValidacaoHabitos() {
        return validacaoHabitos;
    }

    public void setValidacaoHabitos(String validacaoHabitos) {
        this.validacaoHabitos = validacaoHabitos;
    }
}
