package com.OdontoVison.Java.model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_seq")
    @SequenceGenerator(name = "usuario_seq", sequenceName = "usuario_sequence", allocationSize = 1)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, length = 100)
    private String senha;

    @Temporal(TemporalType.DATE)
    private Date dataNascimento;

    @Column(nullable = false, unique = true, length = 11)
    private String cpf;

    @Column(length = 255)
    private String endereco;

    @Column(length = 15)
    private String telefone;

    // Relacionamentos com outras entidades
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pontuacao> pontuacoes;

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Sinistro> sinistros;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UsuarioRecompensa> usuarioRecompensas;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UsuarioNivel> usuarioNiveis;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UsuarioConquista> usuarioConquistas;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LogAtividade> logsAtividade;

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LogFraude> logsFraude;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HistoricoPontuacao> historicoPontuacoes;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ValidacaoChecklist> validacoesChecklist;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public List<Pontuacao> getPontuacoes() {
        return pontuacoes;
    }

    public void setPontuacoes(List<Pontuacao> pontuacoes) {
        this.pontuacoes = pontuacoes;
    }

    public List<Sinistro> getSinistros() {
        return sinistros;
    }

    public void setSinistros(List<Sinistro> sinistros) {
        this.sinistros = sinistros;
    }

    public List<UsuarioRecompensa> getUsuarioRecompensas() {
        return usuarioRecompensas;
    }

    public void setUsuarioRecompensas(List<UsuarioRecompensa> usuarioRecompensas) {
        this.usuarioRecompensas = usuarioRecompensas;
    }

    public List<UsuarioNivel> getUsuarioNiveis() {
        return usuarioNiveis;
    }

    public void setUsuarioNiveis(List<UsuarioNivel> usuarioNiveis) {
        this.usuarioNiveis = usuarioNiveis;
    }

    public List<UsuarioConquista> getUsuarioConquistas() {
        return usuarioConquistas;
    }

    public void setUsuarioConquistas(List<UsuarioConquista> usuarioConquistas) {
        this.usuarioConquistas = usuarioConquistas;
    }

    public List<LogAtividade> getLogsAtividade() {
        return logsAtividade;
    }

    public void setLogsAtividade(List<LogAtividade> logsAtividade) {
        this.logsAtividade = logsAtividade;
    }

    public List<LogFraude> getLogsFraude() {
        return logsFraude;
    }

    public void setLogsFraude(List<LogFraude> logsFraude) {
        this.logsFraude = logsFraude;
    }

    public List<HistoricoPontuacao> getHistoricoPontuacoes() {
        return historicoPontuacoes;
    }

    public void setHistoricoPontuacoes(List<HistoricoPontuacao> historicoPontuacoes) {
        this.historicoPontuacoes = historicoPontuacoes;
    }

    public List<ValidacaoChecklist> getValidacoesChecklist() {
        return validacoesChecklist;
    }

    public void setValidacoesChecklist(List<ValidacaoChecklist> validacoesChecklist) {
        this.validacoesChecklist = validacoesChecklist;
    }

    // Método equals baseado no campo 'id'
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Usuario usuario = (Usuario) o;

        return id != null ? id.equals(usuario.id) : usuario.id == null;
    }

    // Método hashCode baseado no campo 'id'
    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    // Método toString
    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", cpf='" + cpf + '\'' +
                ", endereco='" + endereco + '\'' +
                ", telefone='" + telefone + '\'' +
                '}';
    }
}