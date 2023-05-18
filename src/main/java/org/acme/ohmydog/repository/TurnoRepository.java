package org.acme.ohmydog.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.ohmydog.entities.Turno;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class TurnoRepository implements PanacheRepository<Turno> {

    public void register(Long idPerro, LocalDate fecha, String motivo) {
        Turno turno = new Turno(idPerro, fecha, motivo);
        persist(turno);
    }

    public Turno buscarTurnoPorIdPerro(Long idPerro) {
        return find("idPerro", idPerro).firstResult();
    }

    public List<Turno> listarTurnos() {
        return listAll();
    }

    public List<Turno> listarTurnosPerro(Long idPerro) {
        return listAll().stream()
                .filter(turno -> turno.getIdPerro().equals(idPerro))
                .collect(Collectors.toList());
    }

}
