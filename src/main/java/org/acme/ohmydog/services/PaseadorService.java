package org.acme.ohmydog.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.acme.ohmydog.repository.PaseadorRepository;
import org.acme.ohmydog.requests.PaseadorRequest;

@ApplicationScoped
public class PaseadorService {

    @Inject
    PaseadorRepository paseadorRepository;

    @Transactional
    public boolean register(PaseadorRequest paseadorRequest) {
        if (paseadorRepository.buscarPaseadorPorEmail(paseadorRequest.getEmail()) != null) {
            return false;
        }
        paseadorRepository.register(paseadorRequest.getNombre(), paseadorRequest.getApellido(), paseadorRequest.getDni(),
                paseadorRequest.getTelefono(), paseadorRequest.getEmail(), paseadorRequest.getZona());
        return true;
    }
}
