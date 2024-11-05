package service;

import model.LogFraude;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.LogFraudeRepository;

import java.util.List;

@Service
public class LogFraudeService {

    @Autowired
    private LogFraudeRepository logFraudeRepository;

    // Listar logs de fraude de um usuário
    public List<LogFraude> listarLogsPorUsuario(Long usuarioId) {
        return logFraudeRepository.findByPaciente_Id(usuarioId);  // Corrigido para findByPaciente_Id
    }

    // Obter log de fraude por ID
    public LogFraude obterLogPorId(Long id) {
        return logFraudeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Log de fraude não encontrado."));
    }

    // Listar todos os logs de fraude (geral)
    public List<LogFraude> listarLogsGeral() {
        return logFraudeRepository.findAll();
    }
}