package org.acme.ohmydog.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.acme.ohmydog.entities.Turno;
import org.acme.ohmydog.entities.Usuario;
import org.acme.ohmydog.repository.TurnoRepository;
import org.acme.ohmydog.requests.TurnoRequest;

import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class TurnoService {

    @Inject
    TurnoRepository turnoRepository;

//    @Inject
//    PerroRepository perroRepository;

    @Transactional
    public boolean register(TurnoRequest turnoRequest) {
//        if (perroRepository.buscarPerroPorId(turnoRequest.getIdPerro()) == null) {
//            return false;
//        }
        turnoRepository.register(turnoRequest.getIdPerro(), turnoRequest.getFecha(), turnoRequest.getMotivo());
        return true;
    }

    @Transactional
    public boolean modificarTurno(Long idPerro, LocalDate fecha, String motivo, String estado) {
        Turno turno = turnoRepository.buscarTurnoPorIdPerro(idPerro);
        if (turno == null) {
            return false;
        }
        turno.setIdPerro(idPerro);
        turno.setFecha(fecha);
        turno.setMotivo(motivo);
        turno.setEstado(estado);
        turnoRepository.persist(turno); // Actualizar el usuario en la base de datos
        return true;
    }

    @Transactional
    public List<Turno> listarTurnos() {
        return turnoRepository.listarTurnos();
    }

    @Transactional
    public List<Turno> listarTurnosPerro(Long idPerro) {
        return turnoRepository.listarTurnosPerro(idPerro);
    }

}
