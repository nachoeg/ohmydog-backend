package entities;

import java.time.LocalDate;
import java.time.LocalTime;

public class Turno {
    private Usuario usuario;
    private LocalDate fecha;
    private LocalTime hora;

    public Turno(Usuario usuario, LocalDate fecha, LocalTime hora) {
        this.usuario = usuario;
        this.fecha = fecha;
        this.hora = hora;
    }

}
