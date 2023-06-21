package org.acme.ohmydog.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

@Entity
@Table(name = "GUARDERIA")

public class Guarderia extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "disponibilidad")
    private String disponibilidad;
    @Column(name = "telefono")
    private Long telefono;
    @Column(name = "mail")
    private String mail;
    @Column(name = "localidad")
    private String localidad;
    @Column(name = "direccion")
    private String direccion;

    public Guarderia(String nombre, String disponibilidad, Long telefono, String mail, String localidad, String direccion) {
        this.nombre = nombre;
        this.disponibilidad = disponibilidad;
        this.telefono = telefono;
        this.mail = mail;
        this.localidad = localidad;
        this.direccion = direccion;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getDisponibilidad() {
        return this.disponibilidad;
    }

    public Long getTelefono() {
        return this.telefono;
    }

    public String getMail() {
        return this.mail;
    }

    public String getLocalidad() {
        return this.localidad;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDisponibilidad(String disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public void setTelefono(Long telefono) {
        this.telefono = telefono;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
