package com.OdontoVison.Java.dto.Conquista;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import java.util.Date;

public class ConquistaRequestDTO {

    @NotBlank(message = "Nome é obrigatório.")
    private String nome;

    @NotBlank(message = "Descrição é obrigatória.")
    private String descricao;

    @PositiveOrZero(message = "Pontos bônus devem ser zero ou positivo.")
    private Integer pontosBonus;

    private Date dataExpiracao;

    // Getters e Setters

    public @NotBlank(message = "Nome é obrigatório.") String getNome() {
        return nome;
    }

    public void setNome(@NotBlank(message = "Nome é obrigatório.") String nome) {
        this.nome = nome;
    }

    public @NotBlank(message = "Descrição é obrigatória.") String getDescricao() {
        return descricao;
    }

    public void setDescricao(@NotBlank(message = "Descrição é obrigatória.") String descricao) {
        this.descricao = descricao;
    }

    public @PositiveOrZero(message = "Pontos bônus devem ser zero ou positivo.") Integer getPontosBonus() {
        return pontosBonus;
    }

    public void setPontosBonus(@PositiveOrZero(message = "Pontos bônus devem ser zero ou positivo.") Integer pontosBonus) {
        this.pontosBonus = pontosBonus;
    }

    public Date getDataExpiracao() {
        return dataExpiracao;
    }

    public void setDataExpiracao(Date dataExpiracao) {
        this.dataExpiracao = dataExpiracao;
    }
}
