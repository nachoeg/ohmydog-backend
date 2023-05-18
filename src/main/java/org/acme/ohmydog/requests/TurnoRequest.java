package org.acme.ohmydog.requests;

import java.time.LocalDate;

public class TurnoRequest {
    private Long idPerro;
    private LocalDate fecha;
    private String motivo;
    private String estado;

    public TurnoRequest(Long idPerro, LocalDate fecha, String motivo, String estado) {
        this.idPerro = idPerro;
        this.fecha = fecha;
        this.motivo = motivo;
        this.estado = estado;
    }

    public Long getIdPerro() {
        return this.idPerro;
    }

    public LocalDate getFecha() {
        return this.fecha;
    }

    public String getMotivo() {
        return this.motivo;
    }

    public String getEstado() {
        return this.estado;
    }

    public void setIdPerro(Long idPerro) {
        this.idPerro = idPerro;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
