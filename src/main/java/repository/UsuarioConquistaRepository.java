package repository;

import model.UsuarioConquista;
import model.UsuarioConquistaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioConquistaRepository extends JpaRepository<UsuarioConquista, UsuarioConquistaId> {
}
