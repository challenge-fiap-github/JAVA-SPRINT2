package service;

import model.HistoricoPontuacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.HistoricoPontuacaoRepository;

import java.util.List;

@Service
public class HistoricoPontuacaoService {

    @Autowired
    private HistoricoPontuacaoRepository historicoPontuacaoRepository;

    // Listar histórico por usuário
    public List<HistoricoPontuacao> listarHistoricoPorUsuario(Long usuarioId) {
        return historicoPontuacaoRepository.findByUsuarioId(usuarioId);
    }

    // Obter histórico por ID
    public HistoricoPontuacao obterHistoricoPorId(Long id) {
        return historicoPontuacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Histórico de pontuação não encontrado."));
    }
}