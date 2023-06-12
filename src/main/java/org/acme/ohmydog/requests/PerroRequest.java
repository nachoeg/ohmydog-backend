package org.acme.ohmydog.requests;

import java.time.LocalDate;

public class PerroRequest {

    private Long idUsuario;
    private String nombre;
    private String raza;
    private int edad;
    private String enfermedad;
    private String sexo;
    private String caracteristicas;
    private boolean castrado;
    private LocalDate vacunaAntirrabica;
    private LocalDate vacunaAntienfemerdades;

    public PerroRequest(Long idUsuario, String nombre, String raza, int edad, String enfermedad,
            String sexo, String caracteristicas, boolean castrado, LocalDate vacunaAntirrabica,
            LocalDate vacunaAntienfermedades) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.raza = raza;
        this.edad = edad;
        this.enfermedad = enfermedad;
        this.sexo = sexo;
        this.caracteristicas = caracteristicas;
        this.castrado = castrado;
        this.vacunaAntirrabica = vacunaAntirrabica;
        this.vacunaAntienfemerdades = vacunaAntienfermedades;
    }

    public Long getIdUsuario() {
        return this.idUsuario;
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

    public LocalDate getVacunaAntirrabica() {
        return this.vacunaAntirrabica;
    }

    public LocalDate getVacunaAntienfermedades() {
        return this.vacunaAntienfemerdades;
    }

    public boolean getCastrado() {
        return this.castrado;
    }
}
