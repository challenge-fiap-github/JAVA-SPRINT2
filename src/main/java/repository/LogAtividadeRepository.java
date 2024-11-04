package repository;

import model.LogAtividade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogAtividadeRepository extends JpaRepository<LogAtividade, Long> {
    List<LogAtividade> findByUsuarioId(Long usuarioId);
}
