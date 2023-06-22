package org.acme.ohmydog.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.acme.ohmydog.entities.Guarderia;
import org.acme.ohmydog.repository.GuarderiaRepository;
import org.acme.ohmydog.requests.GuarderiaRequest;

import java.util.List;

@ApplicationScoped
public class GuarderiaService {

    @Inject
    GuarderiaRepository guarderiaRepository;

    @Transactional
    public boolean register(GuarderiaRequest guarderiaRequest) {
        if (guarderiaRepository.buscarGuarderiaPorEmail(guarderiaRequest.getEmail()) != null) {
            return false;
        }
        guarderiaRepository.register(guarderiaRequest.getNombre(), guarderiaRequest.getDisponibilidad(),
                guarderiaRequest.getTelefono(), guarderiaRequest.getEmail(), guarderiaRequest.getLocalidad(),
                guarderiaRequest.getDireccion());
        return true;
    }

    @Transactional
    public boolean modificarGuarderia(Long id, GuarderiaRequest guarderiaRequest) {
        Guarderia guarderia = guarderiaRepository.buscarGuarderiaPorId(id);
        if (guarderia == null) {
            return false;
        }
        guarderia.setNombre(guarderiaRequest.getNombre());
        guarderia.setDisponibilidad(guarderiaRequest.getDisponibilidad());
        guarderia.setTelefono(guarderiaRequest.getTelefono());
        guarderia.setEmail(guarderiaRequest.getEmail());
        guarderia.setLocalidad(guarderiaRequest.getLocalidad());
        guarderia.setDireccion(guarderiaRequest.getDireccion());
        guarderiaRepository.persist(guarderia);
        return true;
    }

    @Transactional
    public boolean eliminarGuarderia(Long id) {
        Guarderia guarderia = guarderiaRepository.buscarGuarderiaPorId(id);
        if (guarderia == null) {
            return false;
        }
        guarderiaRepository.eliminate(guarderia);
        return true;
    }

    @Transactional
    public List<Guarderia> listarGuarderia() {
        return guarderiaRepository.listarGuarderia();
    }
}
