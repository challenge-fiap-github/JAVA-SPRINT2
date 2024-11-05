package dto.ValidacaoChecklist;

import jakarta.validation.constraints.NotNull;

public class ValidacaoChecklistRequestDTO {

    @NotNull(message = "Status de validação é obrigatório.")
    private String statusValidacao; // 'S' ou 'N'

    private Integer pontosBonus;

    // Getters e Setters

    public @NotNull(message = "Status de validação é obrigatório.") String getStatusValidacao() {
        return statusValidacao;
    }

    public void setStatusValidacao(@NotNull(message = "Status de validação é obrigatório.") String statusValidacao) {
        this.statusValidacao = statusValidacao;
    }

    public Integer getPontosBonus() {
        return pontosBonus;
    }

    public void setPontosBonus(Integer pontosBonus) {
        this.pontosBonus = pontosBonus;
    }
}