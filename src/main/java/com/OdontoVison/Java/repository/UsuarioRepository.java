package com.OdontoVison.Java.repository;

import com.OdontoVison.Java.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByEmail(String email); // Encontra um usuário pelo e-mail
}