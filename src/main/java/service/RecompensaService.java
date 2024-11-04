package service;

import model.Recompensa;
import model.Usuario;
import model.UsuarioRecompensa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.RecompensaRepository;
import repository.UsuarioRecompensaRepository;
import repository.UsuarioRepository;

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
                .orElseThrow(() -> new RuntimeException("Recompensa não encontrada."));
    }

    // Resgatar recompensa
    public Recompensa resgatarRecompensa(Long usuarioId, Long recompensaId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
        Recompensa recompensa = obterRecompensaPorId(recompensaId);

        // Verificar se o usuário tem pontos suficientes
        Integer pontosTotais = pontuacaoService.calcularPontosTotais(usuarioId);
        if (pontosTotais < recompensa.getPontosNecessarios()) {
            throw new RuntimeException("Pontos insuficientes para resgatar a recompensa.");
        }

        // Diminuir a quantidade disponível, se aplicável
        if (recompensa.getQuantidadeDisponivel() != null && recompensa.getQuantidadeDisponivel() > 0) {
            recompensa.setQuantidadeDisponivel(recompensa.getQuantidadeDisponivel() - 1);
            recompensaRepository.save(recompensa);
        } else if (recompensa.getQuantidadeDisponivel() != null && recompensa.getQuantidadeDisponivel() <= 0) {
            throw new RuntimeException("Recompensa indisponível.");
        }

        // Registrar o resgate
        UsuarioRecompensa usuarioRecompensa = new UsuarioRecompensa();
        usuarioRecompensa.setUsuario(usuario);
        usuarioRecompensa.setRecompensa(recompensa);
        usuarioRecompensa.setDataResgate(new Date());
        usuarioRecompensaRepository.save(usuarioRecompensa);

        // Atualizar os pontos do usuário (se necessário)
        pontuacaoService.deduzirPontos(usuarioId, recompensa.getPontosNecessarios());

        return recompensa;
    }
}
