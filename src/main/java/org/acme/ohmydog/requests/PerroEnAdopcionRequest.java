package org.acme.ohmydog.requests;

public class PerroEnAdopcionRequest {

    private Long idUsuario;
    private String nombre;
    private String raza;
    private int edad;
    private String sexo;
    private String email;
    private Long telefono;

    public Long getIdUsuario() {
        return idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getRaza() {
        return raza;
    }

    public int getEdad() {
        return edad;
    }

    public String getSexo() {
        return sexo;
    }

    public String getEmail() {
        return email;
    }

    public Long getTelefono() {
        return telefono;
    }
}
