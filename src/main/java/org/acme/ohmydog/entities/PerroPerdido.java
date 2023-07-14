package org.acme.ohmydog.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "PERROPERDIDO")

public class PerroPerdido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "idUsuario")
    private Long idUsuario;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "zona")
    private String zona;
    @Column(name = "fecha")
    private LocalDate fecha;
    @Column(name = "email")
    private String email;
    @Column(name = "estado")
    private String estado;
    @Column(name = "descripcion")
    private String descripcion;

    public PerroPerdido() {
    }

    public PerroPerdido(Long idUsuario, String nombre, String zona, LocalDate fecha, String email, String descripcion) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.zona = zona;
        this.fecha = fecha;
        this.email = email;
        this.descripcion = descripcion;
        this.estado = "Pendiente";
    }

    
    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public Long getId() {
        return this.id;
    }
    public Long getIdUsuario(){
        return this.idUsuario;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getZona() {
        return this.zona;
    }

    public LocalDate getFecha() {
        return this.fecha;
    }

    public String getEmail() {
        return this.email;
    }

    public String getEstado() {
        return this.estado;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
