package org.acme.ohmydog.requests;

public class PaseadorRequest {
    private String nombre;
    private String apellido;
    private Long dni;
    private Long telefono;
    private String email;
    private String zona;

    public PaseadorRequest(String nombre, String apellido, Long dni, Long telefono, String email, String zona) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.telefono = telefono;
        this.email = email;
        this.zona = zona;
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
