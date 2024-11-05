package dto.UsuarioNivel;

import java.util.Date;

public class UsuarioNivelResponseDTO {

    private Long usuarioId;
    private Long nivelId;
    private Integer pontosAtuais;
    private Date dataUltimaAtualizacao;

    // Getters e Setters

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getNivelId() {
        return nivelId;
    }

    public void setNivelId(Long nivelId) {
        this.nivelId = nivelId;
    }

    public Integer getPontosAtuais() {
        return pontosAtuais;
    }

    public void setPontosAtuais(Integer pontosAtuais) {
        this.pontosAtuais = pontosAtuais;
    }

    public Date getDataUltimaAtualizacao() {
        return dataUltimaAtualizacao;
    }

    public void setDataUltimaAtualizacao(Date dataUltimaAtualizacao) {
        this.dataUltimaAtualizacao = dataUltimaAtualizacao;
    }
}
