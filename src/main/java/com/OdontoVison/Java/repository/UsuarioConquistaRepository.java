package com.OdontoVison.Java.repository;

import com.OdontoVison.Java.model.UsuarioConquista;
import com.OdontoVison.Java.model.UsuarioConquistaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioConquistaRepository extends JpaRepository<UsuarioConquista, UsuarioConquistaId> {
}

