package com.OdontoVison.Java.dto.Nivel;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class NivelRequestDTO {

    @NotBlank(message = "Descrição é obrigatória.")
    private String descricao;

    @NotNull(message = "Pontos necessários são obrigatórios.")
    @PositiveOrZero(message = "Pontos necessários devem ser zero ou positivo.")
    private Integer pontosNecessarios;

    // Getters e Setters

    public @NotBlank(message = "Descrição é obrigatória.") String getDescricao() {
        return descricao;
    }

    public void setDescricao(@NotBlank(message = "Descrição é obrigatória.") String descricao) {
        this.descricao = descricao;
    }

    public @NotNull(message = "Pontos necessários são obrigatórios.") @PositiveOrZero(message = "Pontos necessários devem ser zero ou positivo.") Integer getPontosNecessarios() {
        return pontosNecessarios;
    }

    public void setPontosNecessarios(@NotNull(message = "Pontos necessários são obrigatórios.") @PositiveOrZero(message = "Pontos necessários devem ser zero ou positivo.") Integer pontosNecessarios) {
        this.pontosNecessarios = pontosNecessarios;
    }
}
