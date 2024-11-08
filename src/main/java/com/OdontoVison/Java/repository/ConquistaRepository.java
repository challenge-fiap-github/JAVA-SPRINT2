package com.OdontoVison.Java.repository;

import com.OdontoVison.Java.model.Conquista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConquistaRepository extends JpaRepository<Conquista, Long> {

    @Query("SELECT c FROM Conquista c JOIN UsuarioConquista uc ON c.id = uc.id.conquistaId WHERE uc.id.usuarioId = :usuarioId")
    List<Conquista> findConquistasByUsuario(Long usuarioId);
}
