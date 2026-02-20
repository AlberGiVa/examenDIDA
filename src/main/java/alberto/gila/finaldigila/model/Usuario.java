package alberto.gila.finaldigila.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(name = "rol")
    private String rol;

    // --- EQUIPO FAVORITO ---
    // Relación Muchos Usuarios pueden tener 1 Equipo favorito
    @ManyToOne
    @JoinColumn(name = "equipo_favorito_id") // Crea una columna extra en la tabla Usuario
    private Videojuego videojuegoFavorito;

    public Usuario() { }

    public Usuario(String username, String password, String rol) {
        this.username = username;
        this.password = password;
        this.rol = rol;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }

    public Videojuego getEquipoFavorito() { return videojuegoFavorito; }
    public void setEquipoFavorito(Videojuego videojuegoFavorito) { this.videojuegoFavorito = videojuegoFavorito; }
}