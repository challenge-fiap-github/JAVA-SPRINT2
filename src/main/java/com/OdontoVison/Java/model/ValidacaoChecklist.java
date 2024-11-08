package com.OdontoVison.Java.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "validacao_checklist")
public class ValidacaoChecklist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relacionamento com Usuario
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    // Como Consulta está em outra tecnologia, usamos o ID
    @Column(name = "consulta_id", nullable = false)
    private Long consultaId;

    @Temporal(TemporalType.DATE)
    private Date dataValidacao;

    @Column(length = 1)
    private String statusValidacao; // 'S' ou 'N'

    private Integer pontosBonus;

    // Método para definir a data de validação antes de persistir
    @PrePersist
    public void prePersist() {
        this.dataValidacao = new Date();
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Long getConsultaId() {
        return consultaId;
    }

    public void setConsultaId(Long consultaId) {
        this.consultaId = consultaId;
    }

    public Date getDataValidacao() {
        return dataValidacao;
    }

    public void setDataValidacao(Date dataValidacao) {
        this.dataValidacao = dataValidacao;
    }

    public String getStatusValidacao() {
        return statusValidacao;
    }

    public void setStatusValidacao(String statusValidacao) {
        this.statusValidacao = statusValidacao;
    }

    public Integer getPontosBonus() {
        return pontosBonus;
    }

    public void setPontosBonus(Integer pontosBonus) {
        this.pontosBonus = pontosBonus;
    }

    // Método equals baseado no campo 'id'
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ValidacaoChecklist that = (ValidacaoChecklist) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    // Método hashCode baseado no campo 'id'
    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    // Método toString
    @Override
    public String toString() {
        return "ValidacaoChecklist{" +
                "id=" + id +
                ", usuario=" + (usuario != null ? usuario.getId() : "null") +
                ", consultaId=" + consultaId +
                ", dataValidacao=" + dataValidacao +
                ", statusValidacao='" + statusValidacao + '\'' +
                ", pontosBonus=" + pontosBonus +
                '}';
    }
}
