package org.acme.ohmydog.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

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
    }

    public Long getId() {
        return this.id;
    }

    public void agregarTurno(Turno turno) {
        this.turnos.add(turno);
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
}
