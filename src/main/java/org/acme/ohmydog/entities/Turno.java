package org.acme.ohmydog.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "TURNO")
public class Turno extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "perro_id")
    private Perro perro;

    @Column(name = "idPerro")
    private Long idPerro;
    @Column(name = "fecha")
    private LocalDate fecha;
    @Column(name = "motivo")
    private String motivo;
    @Column(name = "estado")
    private String estado;


    public Turno() {
    }

    public Turno(Long idPerro, LocalDate fecha, String motivo) {
        this.idPerro = idPerro;
        this.fecha = fecha;
        this.motivo = motivo;
        this.estado = "pendiente";
    }

    public Long getId() {
        return id;
    }

    public Long getIdPerro() {
        return idPerro;
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

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setIdPerro(Long idPerro) {
        this.idPerro = idPerro;
    }
}
