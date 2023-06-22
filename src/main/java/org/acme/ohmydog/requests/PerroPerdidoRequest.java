package org.acme.ohmydog.requests;

import java.time.LocalDate;

public class PerroPerdidoRequest {
    private String nombre;
    private String raza;
    private String zona;
    private LocalDate fecha;
    private String email;

    public String getNombre() {
        return this.nombre;
    }

    public String getRaza() {
        return this.raza;
    }

    public String getZona() {
        return this.zona;
    }

    public LocalDate getFecha() {
        return this.fecha;
    }

    public String getEmail() {
        return this.email;
    }
}
