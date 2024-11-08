package com.OdontoVison.Java.service;

import com.OdontoVison.Java.exception.EmailAlreadyInUseException;
import com.OdontoVison.Java.exception.UsuarioNotFoundException;
import com.OdontoVison.Java.model.Usuario;
import com.OdontoVison.Java.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Registrar novo usuário
    public Usuario registrarUsuario(Usuario usuario) {
        // Verificar se o email já está em uso
        if (usuarioRepository.findByEmail(usuario.getEmail()) != null) {
            throw new EmailAlreadyInUseException("Email " + usuario.getEmail() + " já está em uso.");
        }

        // Salvar a senha sem codificação (apenas para testes)
        usuario.setSenha(usuario.getSenha());

        return usuarioRepository.save(usuario);
    }

    // Autenticar usuário
    public Usuario autenticarUsuario(String email, String senha) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario == null) {
            throw new UsuarioNotFoundException("Usuário com email " + email + " não encontrado.");
        }

        // Comparar a senha diretamente (sem codificação)
        if (!senha.equals(usuario.getSenha())) {
            throw new IllegalArgumentException("Senha inválida.");
        }

        return usuario;
    }

    // Obter usuário por ID
    public Usuario obterUsuarioPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNotFoundException("Usuário com ID " + id + " não encontrado."));
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

        // Atualizar a senha diretamente (apenas para testes)
        if (usuarioAtualizado.getSenha() != null && !usuarioAtualizado.getSenha().isEmpty()) {
            usuarioExistente.setSenha(usuarioAtualizado.getSenha());
        }

        return usuarioRepository.save(usuarioExistente);
    }

    // Deletar usuário
    public void deletarUsuario(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new UsuarioNotFoundException("Usuário com ID " + id + " não encontrado.");
        }
        usuarioRepository.deleteById(id);
    }
}