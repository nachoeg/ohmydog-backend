package org.acme.ohmydog.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.acme.ohmydog.entities.Perro;
import org.acme.ohmydog.entities.Turno;
import org.acme.ohmydog.repository.PerroRepository;
import org.acme.ohmydog.repository.TurnoRepository;
import org.acme.ohmydog.requests.TurnoRequest;

import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class TurnoService {

    @Inject
    TurnoRepository turnoRepository;

    @Inject
    PerroRepository perroRepository;

    @Transactional
    public boolean register(TurnoRequest turnoRequest) {
        Perro perro = perroRepository.buscarPerroPorId(turnoRequest.getIdPerro());
        if (perro == null) {
            return false;
        }
        Turno turno = turnoRepository.register(perro.getId(), turnoRequest.getFecha(), turnoRequest.getMotivo());
        perro.agregarTurno(turno);
        return true;
    }

    @Transactional
    public boolean modificarTurno(Long id, Long idPerro, LocalDate fecha, String motivo, String estado) {
        Turno turno = turnoRepository.buscarTurnoPorId(id);
        if (turno == null) {
            return false;
        }
        turno.setIdPerro(idPerro);
        turno.setFecha(fecha);
        turno.setMotivo(motivo);
        turno.setEstado(estado);
        turnoRepository.persist(turno);
        return true;
    }

    @Transactional
    public List<Turno> listarTurnos() {
        return turnoRepository.listarTurnos();
    }

    @Transactional
    public List<Turno> listarTurnosPerro(Long idPerro) {
        Perro perro = perroRepository.buscarPerroPorId(idPerro);
        if (perro == null) {
            return null;
        }
        return perro.getTurnos();
    }

}
