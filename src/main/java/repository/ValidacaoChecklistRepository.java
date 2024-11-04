package repository;

import model.ValidacaoChecklist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ValidacaoChecklistRepository extends JpaRepository<ValidacaoChecklist, Long> {
}
