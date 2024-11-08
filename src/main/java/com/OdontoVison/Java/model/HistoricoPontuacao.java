package com.OdontoVison.Java.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "historico_pontuacao")
public class HistoricoPontuacao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "historico_pontuacao_seq")
    @SequenceGenerator(name = "historico_pontuacao_seq", sequenceName = "historico_pontuacao_sequence", allocationSize = 1)
    private Long id;

    // Relacionamento com Usuario
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Temporal(TemporalType.DATE)
    private Date dataConsulta;

    @Column(nullable = false)
    private Integer pontosGanhos;

    private Integer pontosTotais;

    @Column(nullable = false, length = 20)
    private String tipo; // 'Consulta Avaliativa', 'Consulta Preventiva', 'Bônus'

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

    // Método equals baseado no campo 'id'
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HistoricoPontuacao that = (HistoricoPontuacao) o;

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
        return "HistoricoPontuacao{" +
                "id=" + id +
                ", usuario=" + (usuario != null ? usuario.getId() : "null") +
                ", dataConsulta=" + dataConsulta +
                ", pontosGanhos=" + pontosGanhos +
                ", pontosTotais=" + pontosTotais +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}