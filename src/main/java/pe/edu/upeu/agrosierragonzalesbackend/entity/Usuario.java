package pe.edu.upeu.agrosierragonzalesbackend.entity;

import jakarta.persistence.*;
import lombok.Data;
import pe.edu.upeu.agrosierragonzalesbackend.enums.Rol;
import java.util.Set;

@Entity
@Table(name = "usuarios")
@Data
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password; // Cifrada con BCrypt

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Rol rol;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "usuario_privilegios", joinColumns = @JoinColumn(name = "usuario_id"))
    @Column(name = "privilegio")
    private Set<String> privilegios;
}