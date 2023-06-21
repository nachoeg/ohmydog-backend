package org.acme.ohmydog.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.acme.ohmydog.repository.GuarderiaRepository;
import org.acme.ohmydog.requests.GuarderiaRequest;

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
}
