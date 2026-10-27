package pe.edu.upeu.agrosierragonzalesbackend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "lotes")
@Data
public class Lote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigo;
    private String nombre;
    private Double hectareas;
    private String cultivo;
    private String valle;
    private Boolean campaniaCerrada;
    private Double dosisMaximaPorHectarea;
}
