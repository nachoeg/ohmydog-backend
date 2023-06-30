package org.acme.ohmydog.requests;

public class PaseadorRequest {
    private String nombre;
    private String apellido;
    private Long dni;
    private Long telefono;
    private String email;
    private String zona;

    public String getNombre() {
        return this.nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public Long getDni() {
        return this.dni;
    }

    public Long getTelefono() {
        return this.telefono;
    }

    public String getEmail() {
        return this.email;
    }

    public String getZona() {
        return this.zona;
    }
}