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

    // Listar logs de fraude por usuário
    public List<LogFraude> listarLogsPorUsuario(Long usuarioId) {
        return logFraudeRepository.findByPacienteId(usuarioId);
    }

    // Obter log de fraude por ID
    public LogFraude obterLogPorId(Long id) {
        return logFraudeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Log de fraude não encontrado."));
    }
}
