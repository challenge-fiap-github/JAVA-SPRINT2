package com.OdontoVison.Java.dto.Pontuacao;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.util.Date;

public class PontuacaoRequestDTO {

    @NotNull(message = "Pontos são obrigatórios.")
    @PositiveOrZero(message = "Pontos devem ser zero ou positivo.")
    private Integer pontos;

    private Date dataRegistro;

    private Date cicloInicial;
    private Date cicloFinal;

    @NotNull(message = "Tipo é obrigatório.")
    private String tipo; // 'Consulta Preventiva' ou 'Bônus'

    private String validacaoHabitos; // 'S' ou 'N'

    // Getters e Setters

    public @NotNull(message = "Pontos são obrigatórios.") @PositiveOrZero(message = "Pontos devem ser zero ou positivo.") Integer getPontos() {
        return pontos;
    }

    public void setPontos(@NotNull(message = "Pontos são obrigatórios.") @PositiveOrZero(message = "Pontos devem ser zero ou positivo.") Integer pontos) {
        this.pontos = pontos;
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

    public @NotNull(message = "Tipo é obrigatório.") String getTipo() {
        return tipo;
    }

    public void setTipo(@NotNull(message = "Tipo é obrigatório.") String tipo) {
        this.tipo = tipo;
    }

    public String getValidacaoHabitos() {
        return validacaoHabitos;
    }

    public void setValidacaoHabitos(String validacaoHabitos) {
        this.validacaoHabitos = validacaoHabitos;
    }
}