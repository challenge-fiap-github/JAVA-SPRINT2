package com.OdontoVison.Java.repository;

import com.OdontoVison.Java.model.LogFraude;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogFraudeRepository extends JpaRepository<LogFraude, Long> {
    List<LogFraude> findByPaciente_Id(Long pacienteId); // Correção do nome do campo para relacionamento
}