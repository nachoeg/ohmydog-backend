package org.acme.ohmydog.requests;

public class PerroEnAdopcionRequest {

    private Long idUsuario;
    private String nombre;
    private String raza;
    private int edad;
    private String sexo;
    private String email;
    private Long telefono;
    private String enfermedades;
    private String caracteristicas;

    public Long getIdUsuario() {
        return idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEnfermedades() {
        return enfermedades;
    }
    public String getCaracteristicas() {
        return caracteristicas;
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
