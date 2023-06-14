package org.acme.ohmydog.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.ohmydog.entities.Perro;
import org.acme.ohmydog.entities.Turno;
import org.acme.ohmydog.entities.Usuario;

import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class TurnoRepository implements PanacheRepository<Turno> {

    public Turno register(Long idPerro, String nombre, LocalDate fecha, String motivo) {
        Turno turno = new Turno(idPerro, nombre, fecha, motivo);
        persist(turno);
        return turno;
    }

    public Turno buscarTurnoPorId(Long id) {
        return find("id", id).firstResult();
    }

    public List<Turno> listarTurnos() {
        return listAll();
    }

    public List<Turno> listarTurnosPerro(Perro perro) {
        return list("perro", perro);
    }

}
