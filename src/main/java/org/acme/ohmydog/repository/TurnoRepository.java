package org.acme.ohmydog.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.ohmydog.entities.Turno;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class TurnoRepository implements PanacheRepository<Turno> {

    public Turno register(Long idPerro, LocalDate fecha, String motivo) {
        Turno turno = new Turno(idPerro, fecha, motivo);
        persist(turno);
        return turno;
    }

    public Turno buscarTurnoPorId(Long id) {
        return find("id", id).firstResult();
    }

    public List<Turno> listarTurnos() {
        return listAll();
    }

}
