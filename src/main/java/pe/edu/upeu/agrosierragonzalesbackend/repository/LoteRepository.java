package pe.edu.upeu.agrosierragonzalesbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.agrosierragonzalesbackend.entity.Lote;
import java.util.Optional;

public interface LoteRepository extends JpaRepository<Lote, Long> {}
