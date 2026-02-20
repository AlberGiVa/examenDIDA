package alberto.gila.finaldigila.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Videojuego")
public class Videojuego {

    // 1. ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 2. COLUMNAS
    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "completado")
    private boolean completado;

    @Column(name = "fecha_lanzamiento")
    private LocalDate fechaLanzamiento;

    // 3. CONSTRUCTORES

    public Videojuego() {
    }

    public Videojuego(String titulo, boolean completado, LocalDate fechaLanzamiento) {
        this.titulo = titulo;
        this.completado = completado;
        this.fechaLanzamiento = fechaLanzamiento;
    }

    // 4. GETTERS Y SETTERS

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return titulo;
    }

    public void setNombre(String nombre) {
        this.titulo = titulo;
    }

    public boolean isCompletado() {
        return completado;
    }

    public void setCampeon(boolean completado) {
        this.completado = completado;
    }

    public LocalDate getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(LocalDate fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

}