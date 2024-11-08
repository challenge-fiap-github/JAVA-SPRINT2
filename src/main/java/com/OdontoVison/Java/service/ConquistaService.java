package com.OdontoVison.Java.service;

import com.OdontoVison.Java.model.Conquista;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.OdontoVison.Java.repository.ConquistaRepository;
import com.OdontoVison.Java.repository.UsuarioRepository;

import java.util.List;

@Service
public class ConquistaService {

    private final ConquistaRepository conquistaRepository;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public ConquistaService(ConquistaRepository conquistaRepository,
                            UsuarioRepository usuarioRepository) {
        this.conquistaRepository = conquistaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    // Listar conquistas
    public List<Conquista> listarConquistas() {
        return conquistaRepository.findAll();
    }

    // Obter conquista por ID
    public Conquista obterConquistaPorId(Long id) {
        return conquistaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conquista não encontrada."));
    }

    // Listar conquistas por usuário
    public List<Conquista> listarConquistasPorUsuario(Long usuarioId) {
        usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
        return conquistaRepository.findConquistasByUsuario(usuarioId);
    }
}