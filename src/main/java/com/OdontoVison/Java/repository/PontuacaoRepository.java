package com.OdontoVison.Java.repository;

import com.OdontoVison.Java.model.Pontuacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PontuacaoRepository extends JpaRepository<Pontuacao, Long> {
    List<Pontuacao> findByUsuarioId(Long usuarioId);

    @Query("SELECT SUM(p.pontos) FROM Pontuacao p WHERE p.usuario.id = :usuarioId")
    Integer sumPontosByUsuarioId(Long usuarioId);

    //método para somar os pontos deduzidos
    @Query("SELECT SUM(p.pontos) FROM Pontuacao p WHERE p.usuario.id = :usuarioId AND p.pontos < 0")
    Integer sumPontosDeduzidosByUsuarioId(Long usuarioId);
}
