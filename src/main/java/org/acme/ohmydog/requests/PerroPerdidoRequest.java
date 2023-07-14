package org.acme.ohmydog.requests;

import java.time.LocalDate;

public class PerroPerdidoRequest {
    private Long idUsuario;
    private String nombre;
    private String zona;
    private LocalDate fecha;
    private String email;
    private String descripcion;

    public String getDescripcion() {
        return this.descripcion;
    }

    public Long getIdUsuario() {
        return this.idUsuario;
    }

    public String getNombre() {
        return this.nombre;
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
