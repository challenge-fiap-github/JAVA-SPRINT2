package repository;

import model.HistoricoPontuacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoricoPontuacaoRepository extends JpaRepository<HistoricoPontuacao, Long> {
    List<HistoricoPontuacao> findByUsuarioId(Long usuarioId);
}
