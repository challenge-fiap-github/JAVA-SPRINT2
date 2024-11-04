package model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.List;


@Data
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    // Relacionamento com Pontuacao
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pontuacao> pontuacoes;

    // Relacionamento com Sinistro
    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Sinistro> sinistros;

    // Relacionamento com UsuarioRecompensa
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UsuarioRecompensa> usuarioRecompensas;

    // Relacionamento com UsuarioNivel
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UsuarioNivel> usuarioNiveis;

    // Relacionamento com UsuarioConquista
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UsuarioConquista> usuarioConquistas;

    // Relacionamento com LogAtividade
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LogAtividade> logsAtividade;

    // Relacionamento com LogFraude
    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LogFraude> logsFraude;

    // Relacionamento com HistoricoPontuacao
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HistoricoPontuacao> historicoPontuacoes;

    // Relacionamento com ValidacaoChecklist
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ValidacaoChecklist> validacoesChecklist;

}
