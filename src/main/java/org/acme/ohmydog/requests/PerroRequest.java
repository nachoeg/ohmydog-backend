package org.acme.ohmydog.requests;

public class PerroRequest {

    private Long idUsuario;
    private String nombre;
    private String raza;
    private int edad;
    private String enfermedad;
    private String sexo;
    private String caracteristicas;

    public PerroRequest(Long idUsuario, String nombre, String raza, int edad, String enfermedad,
        String sexo, String caracteristicas) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.raza = raza;
        this.edad = edad;
        this.enfermedad = enfermedad;
        this.sexo = sexo;
        this.caracteristicas = caracteristicas;
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

}
