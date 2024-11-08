package com.OdontoVison.Java.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "recompensa")
public class Recompensa {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "recompensa_seq")
    @SequenceGenerator(name = "recompensa_seq", sequenceName = "recompensa_sequence", allocationSize = 1)
    private Long id;

    @Column(nullable = false, length = 255)
    private String descricao;

    @Column(nullable = false)
    private Integer pontosNecessarios;

    private Integer quantidadeDisponivel;

    @Temporal(TemporalType.DATE)
    private Date dataExpiracao;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getPontosNecessarios() {
        return pontosNecessarios;
    }

    public void setPontosNecessarios(Integer pontosNecessarios) {
        this.pontosNecessarios = pontosNecessarios;
    }

    public Integer getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public void setQuantidadeDisponivel(Integer quantidadeDisponivel) {
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

    public Date getDataExpiracao() {
        return dataExpiracao;
    }

    public void setDataExpiracao(Date dataExpiracao) {
        this.dataExpiracao = dataExpiracao;
    }

    // Método equals baseado no campo 'id'
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Recompensa that = (Recompensa) o;

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
        return "Recompensa{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", pontosNecessarios=" + pontosNecessarios +
                ", quantidadeDisponivel=" + quantidadeDisponivel +
                ", dataExpiracao=" + dataExpiracao +
                '}';
    }
}