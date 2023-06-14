package org.acme.ohmydog.requests;

public class PerroEnAdopcionRequest {

    private Long idUsuario;
    private String nombre;
    private String raza;
    private int edad;
    private String sexo;
    private String caracteristicas;
    private String descripcion;
    private String email;
    private Long telefono;

    public Long getIdUsuario() {
        return this.idUsuario;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getDescripcion() {
        return this.descripcion;
    }
    public String getCaracteristicas() {
        return this.caracteristicas;
    }
    public String getRaza() {
        return this.raza;
    }

    public int getEdad() {
        return this.edad;
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
}
