package pe.edu.upeu.agrosierragonzalesbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.agrosierragonzalesbackend.entity.Usuario;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsername(String username);
}
