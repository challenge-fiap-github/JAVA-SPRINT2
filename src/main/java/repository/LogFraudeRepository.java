package repository;

import model.LogFraude;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogFraudeRepository extends JpaRepository<LogFraude, Long> {
    List<LogFraude> findByPacienteId(Long pacienteId);
}
