package pe.edu.upeu.agrosierragonzalesbackend.entity;

import jakarta.persistence.*;
import lombok.Data;
import pe.edu.upeu.agrosierragonzalesbackend.enums.TipoLabor;

@Entity
@Table(name = "labores")
@Data
public class Labor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long loteId;

    @Enumerated(EnumType.STRING)
    private TipoLabor tipo;

    private String fecha; // Formato ISO-8601 (YYYY-MM-DD)
    private String responsable;
    private Double cantidad;
    private String observacion;
}
