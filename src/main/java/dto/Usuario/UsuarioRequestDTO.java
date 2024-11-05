package dto.Usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.Date;

public class UsuarioRequestDTO {

    @NotBlank(message = "Nome é obrigatório.")
    @Size(max = 100)
    private String nome;

    @NotBlank(message = "Email é obrigatório.")
    @Email(message = "Email inválido.")
    @Size(max = 100)
    private String email;

    @NotBlank(message = "Senha é obrigatória.")
    @Size(min = 6, max = 100, message = "A senha deve ter entre 6 e 100 caracteres.")
    private String senha;

    private Date dataNascimento;

    @NotBlank(message = "CPF é obrigatório.")
    @Size(min = 11, max = 11, message = "O CPF deve ter 11 dígitos.")
    private String cpf;

    @Size(max = 255)
    private String endereco;

    @Size(max = 15)
    private String telefone;

    public @NotBlank(message = "Nome é obrigatório.") @Size(max = 100) String getNome() {
        return nome;
    }

    public void setNome(@NotBlank(message = "Nome é obrigatório.") @Size(max = 100) String nome) {
        this.nome = nome;
    }

    public @NotBlank(message = "Email é obrigatório.") @Email(message = "Email inválido.") @Size(max = 100) String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "Email é obrigatório.") @Email(message = "Email inválido.") @Size(max = 100) String email) {
        this.email = email;
    }

    public @NotBlank(message = "Senha é obrigatória.") @Size(min = 6, max = 100, message = "A senha deve ter entre 6 e 100 caracteres.") String getSenha() {
        return senha;
    }

    public void setSenha(@NotBlank(message = "Senha é obrigatória.") @Size(min = 6, max = 100, message = "A senha deve ter entre 6 e 100 caracteres.") String senha) {
        this.senha = senha;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public @NotBlank(message = "CPF é obrigatório.") @Size(min = 11, max = 11, message = "O CPF deve ter 11 dígitos.") String getCpf() {
        return cpf;
    }

    public void setCpf(@NotBlank(message = "CPF é obrigatório.") @Size(min = 11, max = 11, message = "O CPF deve ter 11 dígitos.") String cpf) {
        this.cpf = cpf;
    }

    public @Size(max = 255) String getEndereco() {
        return endereco;
    }

    public void setEndereco(@Size(max = 255) String endereco) {
        this.endereco = endereco;
    }

    public @Size(max = 15) String getTelefone() {
        return telefone;
    }

    public void setTelefone(@Size(max = 15) String telefone) {
        this.telefone = telefone;
    }
}