package org.acme.ohmydog.requests;

public class UsuarioRequest {
    private String email;
    private String password;
    private String nombre;
    private String apellido;
    private Long dni;
    private String localidad;
    private String direccion;
    private Long telefono;

    public UsuarioRequest(String email, String password, String nombre, String apellido, Long dni, String localidad, String direccion, Long telefono) {
        this.email = email;
        this.password = password;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.localidad = localidad;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public Long getDni() {
        return dni;
    }

    public String getLocalidad() {
        return localidad;
    }

    public String getDireccion() {
        return direccion;
    }

    public Long getTelefono() {
        return telefono;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
