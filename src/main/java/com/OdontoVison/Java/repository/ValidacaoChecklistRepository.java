package com.OdontoVison.Java.repository;

import com.OdontoVison.Java.model.ValidacaoChecklist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ValidacaoChecklistRepository extends JpaRepository<ValidacaoChecklist, Long> {
    List<ValidacaoChecklist> findByUsuarioId(Long usuarioId);
}

