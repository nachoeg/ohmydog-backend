package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity // Indicia que la clase Usuario es una entidad, por lo que esta asociada a una tabla en la base de datos
@Table(name = "usuarios")
public class Usuario {
    @Id // Indica que el atributo email es la clave primaria de la tabla de base de datos que representa esta entidad
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "contraseña")
    private String contrasena;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "dni")
    private Long dni;

    @Column(name = "localidad")
    private String localidad;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "telefono")
    private Long telefono;

    @Column(name = "perros")
    private List<Perro> perros;

    public Usuario(String email, String contrasena, String nombre, String apellido, Long dni, String localidad,
                   String direccion, Long telefono) {
        this.email = email;
        this.contrasena = contrasena;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.localidad = localidad;
        this.direccion = direccion;
        this.telefono = telefono;
        this.perros = new ArrayList<>();
    }

    public String getEmail() {
        return this.email;
    }

    public String getContrasena() {
        return this.contrasena;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setDni(Long dni) {
        this.dni = dni;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTelefono(Long telefono) {
        this.telefono = telefono;
    }
}
