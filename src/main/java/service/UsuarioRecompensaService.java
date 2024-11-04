package service;

import model.UsuarioRecompensa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.UsuarioRecompensaRepository;

import java.util.List;

@Service
public class UsuarioRecompensaService {

    @Autowired
    private UsuarioRecompensaRepository usuarioRecompensaRepository;

    // Listar recompensas resgatadas por usuário
    public List<UsuarioRecompensa> listarRecompensasPorUsuario(Long usuarioId) {
        return usuarioRecompensaRepository.findByUsuarioId(usuarioId);
    }

    // Obter recompensa resgatada por ID
    public UsuarioRecompensa obterRecompensaResgatadaPorId(Long id) {
        return usuarioRecompensaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recompensa resgatada não encontrada."));
    }
}
