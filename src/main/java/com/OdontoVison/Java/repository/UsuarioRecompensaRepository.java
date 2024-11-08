package com.OdontoVison.Java.repository;

import com.OdontoVison.Java.model.UsuarioRecompensa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRecompensaRepository extends JpaRepository<UsuarioRecompensa, Long> {
    List<UsuarioRecompensa> findByUsuarioId(Long usuarioId);
}
