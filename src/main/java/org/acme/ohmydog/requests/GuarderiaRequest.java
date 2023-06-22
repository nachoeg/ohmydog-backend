package org.acme.ohmydog.requests;

public class GuarderiaRequest {
    private String nombre;
    private String disponibilidad;
    private Long telefono;
    private String email;
    private String localidad;
    private String direccion;

    public String getNombre() {
        return this.nombre;
    }

    public String getDisponibilidad() {
        return this.disponibilidad;
    }

    public Long getTelefono() {
        return this.telefono;
    }

    public String getEmail() {
        return this.email;
    }

    public String getLocalidad() {
        return this.localidad;
    }

    public String getDireccion() {
        return this.direccion;
    }

}
