package org.acme.ohmydog.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.acme.ohmydog.entities.Perro;
import org.acme.ohmydog.entities.Turno;
import org.acme.ohmydog.entities.Usuario;
import org.acme.ohmydog.excepciones.ExcepcionCastrado;
import org.acme.ohmydog.excepciones.ExcepcionTurno;
import org.acme.ohmydog.repository.PerroRepository;
import org.acme.ohmydog.repository.TurnoRepository;
import org.acme.ohmydog.repository.UsuarioRepository;
import org.acme.ohmydog.requests.TurnoRequest;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@ApplicationScoped
public class TurnoService {

    @Inject
    TurnoRepository turnoRepository;

    @Inject
    PerroRepository perroRepository;

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    PerroService perroService;

    @Transactional
    public boolean register(TurnoRequest turnoRequest) throws ExcepcionCastrado, ExcepcionTurno {
        Perro perro = perroRepository.buscarPerroPorId(turnoRequest.getIdPerro());
        if (perro == null) {
            return false;
        }
        if (perro.puedeTomarTurno(turnoRequest.getMotivo())) {
            switch (turnoRequest.getMotivo()) {
                case "Vacuna Antirrabica":
                    if (!perro.puedeVacunaAntirrabica(turnoRequest.getFecha())) {
                        return false;
                    }
                    break;
                case "Vacuna Antienfermedades":
                    if (!perro.puedeVacunaAntienfermedades(turnoRequest.getFecha())) {
                        return false;
                    }
                    break;
                case "Castracion":
                    if (perro.noPuedeCastracion()) {
                        throw new ExcepcionCastrado("El perro no puede castrarse.");
                    }
                    break;
            }
        }
        else {
            throw new ExcepcionTurno("No puede registrar un turno teniendo uno pendiente.");
        }
        Turno turno = turnoRepository.register(perro.getId(), perro.getNombre(), turnoRequest.getFecha(), turnoRequest.getMotivo());
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
        if (Objects.equals(estado, "Asistio")) {
            Perro perro = perroRepository.buscarPerroPorId( idPerro );
            switch (motivo) {
                case "Vacuna Antirrabica":
                    perroService.modificarPerro(perro.getId(), perro.getNombre(), perro.getRaza(), perro.getEdad(), perro.getEnfermedad(),perro.getSexo(), perro.getCaracteristicas(), perro.getCastrado(), fecha, perro.getVacunaAntienfermedades());
                    break;
                case "Vacuna Antienfermedades":
                    perroService.modificarPerro(perro.getId(), perro.getNombre(), perro.getRaza(), perro.getEdad(), perro.getEnfermedad(),perro.getSexo(), perro.getCaracteristicas(), perro.getCastrado(), perro.getVacunaAntirrabica(), fecha);
                    break;
                case "Castracion":
                    perroService.modificarPerro(perro.getId(), perro.getNombre(), perro.getRaza(), perro.getEdad(), perro.getEnfermedad(),perro.getSexo(), perro.getCaracteristicas(), true, perro.getVacunaAntirrabica(), perro.getVacunaAntienfermedades());
                    break;
            }
        }
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
        return turnoRepository.listarTurnosPerro(perro);
    }

    @Transactional
    public List<Turno> listarTurnosUsuario(Long idUsuario) {
        Usuario usuario = usuarioRepository.buscarUsuarioPorId(idUsuario);
        if (usuario == null) {
            return null;
        }
        return usuario.getPerros().stream()
                .flatMap(perro -> perro.getTurnos().stream())
                .collect(Collectors.toList());
    }
}
