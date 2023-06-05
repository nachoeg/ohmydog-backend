package org.acme.ohmydog.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity // Indicia que la clase Usuario es una entidad, por lo que esta asociada a una tabla en la base de datos
@Table(name = "USUARIO")
public class Usuario extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "contraseña")
    private String password;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "dni", unique = true)
    private Long dni;
    @Column(name = "localidad")
    private String localidad;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "telefono")
    private Long telefono;
    @Column(name = "rol")
    private String rol;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id")
    private List<Perro> perros;

    public Usuario() {
    }

    public Usuario(String email, String password, String nombre, String apellido, Long dni, String localidad,
                   String direccion, Long telefono, String rol) {
        this.email = email;
        this.password = password;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.localidad = localidad;
        this.direccion = direccion;
        this.telefono = telefono;
        this.rol = rol;
        this.perros = new ArrayList<>();
    }

    public boolean esVeterinario() { return Objects.equals(this.rol, "veterinario"); }

    public boolean esCliente() { return Objects.equals(this.rol, "cliente"); }

    public void agregarPerro(Perro perro) {
        this.perros.add(perro);
    }

    public boolean eliminarPerro(Perro perro) {
        return this.perros.remove(perro);
    }

    public boolean coincidePassword(String contrasena) {
        return this.getPassword().equals(contrasena);
    }

    public Long getId() {
        return this.id;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public Long getDni() {
        return this.dni;
    }

    public String getLocalidad() {
        return this.localidad;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public Long getTelefono() {
        return this.telefono;
    }

    public String getRol() {
        return this.rol;
    }

    public List<Perro> getPerros(){
        return this.perros;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public void setRol(String rol) {
        this.rol = rol;
    }

}
