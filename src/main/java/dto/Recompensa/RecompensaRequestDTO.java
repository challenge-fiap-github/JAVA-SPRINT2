package dto.Recompensa;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.util.Date;

public class RecompensaRequestDTO {

    @NotBlank(message = "Descrição é obrigatória.")
    private String descricao;

    @NotNull(message = "Pontos necessários são obrigatórios.")
    @PositiveOrZero(message = "Pontos necessários devem ser zero ou positivo.")
    private Integer pontosNecessarios;

    @PositiveOrZero(message = "Quantidade disponível deve ser zero ou positivo.")
    private Integer quantidadeDisponivel;

    private Date dataExpiracao;

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

    public @PositiveOrZero(message = "Quantidade disponível deve ser zero ou positivo.") Integer getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public void setQuantidadeDisponivel(@PositiveOrZero(message = "Quantidade disponível deve ser zero ou positivo.") Integer quantidadeDisponivel) {
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

    public Date getDataExpiracao() {
        return dataExpiracao;
    }

    public void setDataExpiracao(Date dataExpiracao) {
        this.dataExpiracao = dataExpiracao;
    }
}
