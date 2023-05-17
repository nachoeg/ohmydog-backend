package org.acme.ohmydog.requests;

public class ModifyRequest {
    private Long dni;
    private String email;
    private Long telefono;
    private String direccion;
    private String localidad;

    public ModifyRequest(Long dni, String email, Long telefono, String direccion, String localidad) {
        this.dni = dni;
        this.email = email;
        this.telefono = telefono;
        this.direccion = direccion;
        this.localidad = localidad;
    }

    public Long getDni() {
        return this.dni;
    }
    public String getEmail() {
        return this.email;
    }

    public Long getTelefono() {
        return this.telefono;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public String getLocalidad() {
        return this.localidad;
    }

    public void setDni(Long dni) {
        this.dni = dni;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefono(Long telefono) {
        this.telefono = telefono;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }
}
