package com.OdontoVison.Java.service;

import com.OdontoVison.Java.exception.RecompensaNotFoundException;
import com.OdontoVison.Java.exception.UsuarioNotFoundException;
import com.OdontoVison.Java.exception.InsufficientPointsException;
import com.OdontoVison.Java.exception.RecompensaUnavailableException;
import com.OdontoVison.Java.model.Recompensa;
import com.OdontoVison.Java.model.Usuario;
import com.OdontoVison.Java.model.UsuarioRecompensa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.OdontoVison.Java.repository.RecompensaRepository;
import com.OdontoVison.Java.repository.UsuarioRecompensaRepository;
import com.OdontoVison.Java.repository.UsuarioRepository;

import java.util.Date;
import java.util.List;

@Service
public class RecompensaService {

    @Autowired
    private RecompensaRepository recompensaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioRecompensaRepository usuarioRecompensaRepository;

    @Autowired
    private PontuacaoService pontuacaoService;

    // Listar recompensas disponíveis
    public List<Recompensa> listarRecompensasDisponiveis() {
        return recompensaRepository.findRecompensasDisponiveis(new Date());
    }

    // Obter recompensa por ID
    public Recompensa obterRecompensaPorId(Long id) {
        return recompensaRepository.findById(id)
                .orElseThrow(() -> new RecompensaNotFoundException("Recompensa com ID " + id + " não encontrada."));
    }

    // Resgatar recompensa
    public Recompensa resgatarRecompensa(Long usuarioId, Long recompensaId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new UsuarioNotFoundException("Usuário com ID " + usuarioId + " não encontrado."));
        Recompensa recompensa = obterRecompensaPorId(recompensaId);

        // Verificar se o usuário tem pontos suficientes
        Integer pontosTotais = pontuacaoService.calcularPontosTotais(usuarioId);
        if (pontosTotais < recompensa.getPontosNecessarios()) {
            throw new InsufficientPointsException("Pontos insuficientes para resgatar a recompensa.");
        }

        // Diminuir a quantidade disponível, se aplicável
        if (recompensa.getQuantidadeDisponivel() != null && recompensa.getQuantidadeDisponivel() > 0) {
            recompensa.setQuantidadeDisponivel(recompensa.getQuantidadeDisponivel() - 1);
            recompensaRepository.save(recompensa);
        } else if (recompensa.getQuantidadeDisponivel() != null && recompensa.getQuantidadeDisponivel() <= 0) {
            throw new RecompensaUnavailableException("Recompensa indisponível.");
        }

        // Registrar o resgate
        UsuarioRecompensa usuarioRecompensa = new UsuarioRecompensa();
        usuarioRecompensa.setUsuario(usuario);
        usuarioRecompensa.setRecompensa(recompensa);
        usuarioRecompensa.setDataResgate(new Date());
        usuarioRecompensaRepository.save(usuarioRecompensa);

        // Atualizar os pontos do usuário
        pontuacaoService.deduzirPontos(usuarioId, recompensa.getPontosNecessarios());

        return recompensa;
    }
}