package service;

import model.Pontuacao;
import model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.PontuacaoRepository;
import repository.UsuarioRepository;

import java.util.List;

@Service
public class PontuacaoService {

    @Autowired
    private PontuacaoRepository pontuacaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Listar pontuações por usuário
    public List<Pontuacao> listarPontuacoesPorUsuario(Long usuarioId) {
        return pontuacaoRepository.findByUsuarioId(usuarioId);
    }

    // Obter pontuação por ID
    public Pontuacao obterPontuacaoPorId(Long id) {
        return pontuacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pontuação não encontrada."));
    }

    // Registrar nova pontuação
    public Pontuacao registrarPontuacao(Long usuarioId, Pontuacao pontuacao) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));

        pontuacao.setUsuario(usuario);
        pontuacao.setDataRegistro(new java.util.Date());

        // Calcular pontos acumulados
        Integer pontosAcumulados = pontuacaoRepository.sumPontosByUsuarioId(usuarioId);
        pontuacao.setPontosAcumulados((pontosAcumulados != null ? pontosAcumulados : 0) + pontuacao.getPontos());

        return pontuacaoRepository.save(pontuacao);
    }
    // Calcular pontos totais do usuário
    public Integer calcularPontosTotais(Long usuarioId) {
        Integer pontosAcumulados = pontuacaoRepository.sumPontosByUsuarioId(usuarioId);
        Integer pontosDeduzidos = pontuacaoRepository.sumPontosDeduzidosByUsuarioId(usuarioId);
        return (pontosAcumulados != null ? pontosAcumulados : 0) - (pontosDeduzidos != null ? pontosDeduzidos : 0);
    }

    // Deduzir pontos após resgate de recompensa
    public void deduzirPontos(Long usuarioId, Integer pontos) {
        Pontuacao pontuacaoDeduzida = new Pontuacao();
        pontuacaoDeduzida.setUsuario(usuarioRepository.findById(usuarioId).orElseThrow(() -> new RuntimeException("Usuário não encontrado.")));
        pontuacaoDeduzida.setPontos(-pontos);
        pontuacaoDeduzida.setTipo("Resgate de Recompensa");
        pontuacaoDeduzida.setDataRegistro(new java.util.Date());

        Integer pontosAcumulados = pontuacaoRepository.sumPontosByUsuarioId(usuarioId);
        pontuacaoDeduzida.setPontosAcumulados((pontosAcumulados != null ? pontosAcumulados : 0) - pontos);

        pontuacaoRepository.save(pontuacaoDeduzida);
    }
}
