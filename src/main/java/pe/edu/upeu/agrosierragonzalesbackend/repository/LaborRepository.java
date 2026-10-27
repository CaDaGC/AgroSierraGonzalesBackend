package pe.edu.upeu.agrosierragonzalesbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.agrosierragonzalesbackend.entity.Labor;
import java.util.List;

public interface LaborRepository extends JpaRepository<Labor, Long> {
    List<Labor> findByLoteIdOrderByFechaDesc(Long loteId);
}
