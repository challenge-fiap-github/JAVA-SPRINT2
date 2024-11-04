package service;

import model.Usuario;
import model.ValidacaoChecklist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.UsuarioRepository;
import repository.ValidacaoChecklistRepository;

@Service
public class ValidacaoChecklistService {

    @Autowired
    private ValidacaoChecklistRepository validacaoChecklistRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Validar checklist
    public ValidacaoChecklist validarChecklist(Long usuarioId, Long consultaId, ValidacaoChecklist validacao) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));

        validacao.setUsuario(usuario);
        validacao.setConsultaId(consultaId);
        validacao.setDataValidacao(new java.util.Date());

        return validacaoChecklistRepository.save(validacao);
    }

    // Obter validação por ID
    public ValidacaoChecklist obterValidacaoPorId(Long id) {
        return validacaoChecklistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Validação não encontrada."));
    }
}
