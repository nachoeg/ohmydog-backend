package org.acme.ohmydog.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

@Entity // Indicia que la clase Usuario es una entidad, por lo que esta asociada a una tabla en la base de datos
@Table(name = "usuarios")
public class Usuario extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "email", unique = true)
    private String email;
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

    public Usuario() {
    }

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
    }

    public Long getId() {
        return this.id;
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
