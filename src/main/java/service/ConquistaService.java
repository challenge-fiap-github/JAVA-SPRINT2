package service;

import model.Conquista;
import model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.ConquistaRepository;
import repository.UsuarioConquistaRepository;
import repository.UsuarioRepository;

import java.util.List;

@Service
public class ConquistaService {

    @Autowired
    private ConquistaRepository conquistaRepository;

    @Autowired
    private UsuarioConquistaRepository usuarioConquistaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Listar conquistas
    public List<Conquista> listarConquistas() {
        return conquistaRepository.findAll();
    }

    // Obter conquista por ID
    public Conquista obterConquistaPorId(Long id) {
        return conquistaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conquista não encontrada."));
    }

    // Listar conquistas por usuário
    public List<Conquista> listarConquistasPorUsuario(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
        return conquistaRepository.findConquistasByUsuario(usuarioId);
    }
}
