package pe.edu.upeu.agrosierragonzalesbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.agrosierragonzalesbackend.entity.Labor;
import pe.edu.upeu.agrosierragonzalesbackend.entity.Lote;
import pe.edu.upeu.agrosierragonzalesbackend.enums.TipoLabor;
import pe.edu.upeu.agrosierragonzalesbackend.repository.LaborRepository;
import pe.edu.upeu.agrosierragonzalesbackend.repository.LoteRepository;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AgroService {
    private final LoteRepository loteRepository;
    private final LaborRepository laborRepository;

    public Labor registrarLabor(Labor labor) {
        Lote lote = loteRepository.findById(labor.getLoteId())
                .orElseThrow(() -> new RuntimeException("Lote no encontrado"));

        // RN-01: No se puede registrar labor si la campaña está cerrada
        if (Boolean.TRUE.equals(lote.getCampaniaCerrada())) {
            throw new IllegalStateException("RN-01: No se puede registrar labor en un lote con campaña cerrada.");
        }

        // RN-04: Control de dosis máxima en fumigación
        if (labor.getTipo() == TipoLabor.FUMIGACION) {
            double maxPermitido = lote.getDosisMaximaPorHectarea() * lote.getHectareas();
            if (labor.getCantidad() > maxPermitido) {
                throw new IllegalArgumentException("RN-04: La cantidad aplicada supera la dosis máxima permitida para el lote.");
            }
        }

        return laborRepository.save(labor);
    }

    public void cerrarCampania(Long loteId) {
        Lote lote = loteRepository.findById(loteId)
                .orElseThrow(() -> new RuntimeException("Lote no encontrado"));

        List<Labor> labores = laborRepository.findByLoteIdOrderByFechaDesc(loteId);

        // RN-03: La campaña no puede cerrarse si no tiene al menos una labor de tipo COSECHA
        boolean tieneCosecha = labores.stream().anyMatch(l -> l.getTipo() == TipoLabor.COSECHA);
        if (!tieneCosecha) {
            throw new IllegalStateException("RN-03: La campaña no puede cerrarse si el lote no tiene al menos una labor de tipo Cosecha.");
        }

        lote.setCampaniaCerrada(true);
        loteRepository.save(lote);
    }
}
