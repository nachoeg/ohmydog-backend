package org.acme.ohmydog.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

@Entity
@Table(name = "PERROENADOPCION")

public class PerroEnAdopcion extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "idUsuario")
    private Long idUsuario;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "raza")
    private String raza;
    @Column(name = "edad")
    private int edad;
    @Column(name = "sexo")
    private String sexo;
    @Column(name = "email")
    private String email;
    @Column(name = "telefono")
    private Long telefono;
    @Column(name = "estado")
    private String estado;
    @Column(name = "caracteristicas")
    private String caracteristicas;
    @Column(name = "descripcion")
    private String descripcion;
    

    public PerroEnAdopcion() {

    }

    public PerroEnAdopcion(Long idUsuario, String nombre, String raza, int edad, String sexo, String email, Long telefono, String caracteristicas, String descripcion) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.raza = raza;
        this.edad = edad;
        this.sexo = sexo;
        this.email = email;
        this.telefono = telefono;
        this.caracteristicas = caracteristicas;
        this.descripcion = descripcion;
        this.estado = "Pendiente";
    }

    public void setEstado(String estado){
        this.estado = estado;
    }

    public Long getIdUsuario(){
        return this.idUsuario;
    }

    public Long getId() {
        return this.id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getRaza() {
        return this.raza;
    }

    public int getEdad() {
        return this.edad;
    }

    public String getEstado() {
        return this.estado;
    }

    public String getSexo() {
        return this.sexo;
    }

    public String getEmail() {
        return this.email;
    }

    public Long getTelefono() {
        return this.telefono;
    }

    public String getCaracteristicas() {
        return this.caracteristicas;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefono(Long telefono) {
        this.telefono = telefono;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
