package org.acme.ohmydog.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PERRO")

public class Perro extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "raza")
    private String raza;
    @Column(name = "edad")
    private int edad;
    @Column(name = "enfermedad")
    private String enfermedad;
    @Column(name = "sexo")
    private String sexo;
    @Column(name = "caracteristicas")
    private String caracteristicas;

    @Column(name = "vacunaAntirrabica")
    private LocalDate vacunaAntirrabica;

    @Column(name = "vacunaAntienfermedades")
    private LocalDate vacunaAntienfermedades;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "perro_id")
    private List<Turno> turnos;

    public Perro(){
    }

    public Perro(String nombre, String raza, int edad, String enfermedad,
        String sexo, String caracteristicas) {
        this.nombre = nombre;
        this.raza = raza;
        this.edad = edad;
        this.enfermedad = enfermedad;
        this.sexo = sexo;
        this.caracteristicas = caracteristicas;
        this.turnos = new ArrayList<>();
        this.vacunaAntirrabica = null;
        this.vacunaAntienfermedades = null;
    }

    public Long getId() {
        return this.id;
    }

    public void agregarTurno(Turno turno) {
        this.turnos.add(turno);
    }

    public boolean puedeVacunaAntirrabica(LocalDate fecha) {
        return this.getEdad() > 4;

    }

    public boolean puedeVacunaAntienfermedades(LocalDate fecha) {
        return this.getEdad() > 2;
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

    public String getEnfermedad() {
        return this.enfermedad;
    }

    public String getSexo() {
        return this.sexo;
    }

    public String getCaracteristicas() {
        return this.caracteristicas;
    }

    public List<Turno> getTurnos() {
        return turnos;
    }

    public LocalDate getVacunaAntirrabica() {
        return this.vacunaAntirrabica;
    }

    public LocalDate getVacunaAntienfermedades() {
        return this.vacunaAntienfermedades;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setEnfermedad(String enfermedad) {
        this.enfermedad = enfermedad;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public void setVacunaAntirrabica(LocalDate vacunaAntirrabica) {
        this.vacunaAntirrabica = vacunaAntirrabica;
    }

    public void setVacunaAntienfermedades(LocalDate vacunaAntienfermedades) {
        this.vacunaAntienfermedades = vacunaAntienfermedades;
    }
}
