package com.OdontoVison.Java.service;

import com.OdontoVison.Java.model.Nivel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.OdontoVison.Java.repository.NivelRepository;

import java.util.List;

@Service
public class NivelService {

    @Autowired
    private NivelRepository nivelRepository;

    // Listar níveis
    public List<Nivel> listarNiveis() {
        return nivelRepository.findAll();
    }

    // Obter nível por ID
    public Nivel obterNivelPorId(Long id) {
        return nivelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nível não encontrado."));
    }
}
