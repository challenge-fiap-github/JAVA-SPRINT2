package service;

import model.LogAtividade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.LogAtividadeRepository;

import java.util.List;

@Service
public class LogAtividadeService {

    @Autowired
    private LogAtividadeRepository logAtividadeRepository;

    // Listar logs de atividade de um usuário
    public List<LogAtividade> listarLogsPorUsuario(Long usuarioId) {
        return logAtividadeRepository.findByUsuarioId(usuarioId);
    }

    // Obter log de atividade por ID
    public LogAtividade obterLogPorId(Long id) {
        return logAtividadeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Log de atividade não encontrado."));
    }

    // Listar todos os logs de atividade (geral)
    public List<LogAtividade> listarLogsGeral() {
        return logAtividadeRepository.findAll();
    }
}
