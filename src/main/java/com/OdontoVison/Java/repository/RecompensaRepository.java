package com.OdontoVison.Java.repository;

import com.OdontoVison.Java.model.Recompensa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RecompensaRepository extends JpaRepository<Recompensa, Long> {

    @Query("SELECT r FROM Recompensa r WHERE (r.dataExpiracao IS NULL OR r.dataExpiracao >= :dataAtual) AND (r.quantidadeDisponivel IS NULL OR r.quantidadeDisponivel > 0)")
    List<Recompensa> findRecompensasDisponiveis(Date dataAtual);
}
