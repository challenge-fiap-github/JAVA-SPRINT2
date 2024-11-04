package service;

import model.Sinistro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.SinistroRepository;

import java.util.List;

@Service
public class SinistroService {

    @Autowired
    private SinistroRepository sinistroRepository;

    // Listar sinistros por usuário
    public List<Sinistro> listarSinistrosPorUsuario(Long usuarioId) {
        return sinistroRepository.findByPacienteId(usuarioId);
    }

    // Obter sinistro por ID
    public Sinistro obterSinistroPorId(Long id) {
        return sinistroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sinistro não encontrado."));
    }

    // Registrar novo sinistro
    public Sinistro registrarSinistro(Sinistro sinistro) {
        // Validar dados se necessário
        sinistro.setDataSinistro(new java.util.Date());
        return sinistroRepository.save(sinistro);
    }
}
