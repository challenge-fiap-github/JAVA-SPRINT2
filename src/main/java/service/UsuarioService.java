package service;

import model.Usuario;
import repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Se estiver usando codificação de senha
    @Autowired
    private PasswordEncoder passwordEncoder;

    // Registrar novo usuário
    public Usuario registrarUsuario(Usuario usuario) {
        // Verificar se o email já está em uso
        if (usuarioRepository.findByEmail(usuario.getEmail()) != null) {
            throw new RuntimeException("Email já está em uso.");
        }

        // Codificar a senha
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));

        return usuarioRepository.save(usuario);
    }

    // Autenticar usuário
    public Usuario autenticarUsuario(String email, String senha) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario == null) {
            throw new RuntimeException("Usuário não encontrado.");
        }

        // Verificar a senha
        if (!passwordEncoder.matches(senha, usuario.getSenha())) {
            throw new RuntimeException("Senha inválida.");
        }

        return usuario;
    }

    // Obter usuário por ID
    public Usuario obterUsuarioPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
    }

    // Listar todos os usuários
    public List<Usuario> listarTodosUsuarios() {
        return usuarioRepository.findAll();
    }

    // Atualizar usuário
    public Usuario atualizarUsuario(Long id, Usuario usuarioAtualizado) {
        Usuario usuarioExistente = obterUsuarioPorId(id);

        usuarioExistente.setNome(usuarioAtualizado.getNome());
        usuarioExistente.setEmail(usuarioAtualizado.getEmail());
        usuarioExistente.setEndereco(usuarioAtualizado.getEndereco());
        usuarioExistente.setTelefone(usuarioAtualizado.getTelefone());
        usuarioExistente.setDataNascimento(usuarioAtualizado.getDataNascimento());
        // Se quiser atualizar a senha
        if (usuarioAtualizado.getSenha() != null && !usuarioAtualizado.getSenha().isEmpty()) {
            usuarioExistente.setSenha(passwordEncoder.encode(usuarioAtualizado.getSenha()));
        }

        return usuarioRepository.save(usuarioExistente);
    }

    // Deletar usuário
    public void deletarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}
