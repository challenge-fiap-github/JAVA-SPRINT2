package service;

import model.ValidacaoChecklist;
import model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.ValidacaoChecklistRepository;
import repository.UsuarioRepository;

import java.util.List;

@Service
public class ValidacaoChecklistService {

    @Autowired
    private ValidacaoChecklistRepository validacaoChecklistRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Método para validar checklist de um usuário
    public ValidacaoChecklist validarChecklist(Long usuarioId, Long consultaId, ValidacaoChecklist validacao) {
        // Obtenha o usuário pelo ID
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));

        // Configure o usuário e a consultaId na validação
        validacao.setUsuario(usuario);
        validacao.setConsultaId(consultaId);

        return validacaoChecklistRepository.save(validacao);
    }

    // Obter validação por ID
    public ValidacaoChecklist obterValidacaoPorId(Long id) {
        return validacaoChecklistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Validação de checklist não encontrada."));
    }

    // Listar todas as validações de checklist para um usuário específico
    public List<ValidacaoChecklist> listarValidacoesPorUsuario(Long usuarioId) {
        return validacaoChecklistRepository.findByUsuarioId(usuarioId);
    }

    // Listar todas as validações de checklist (geral)
    public List<ValidacaoChecklist> listarTodasValidacoes() {
        return validacaoChecklistRepository.findAll();
    }
}

