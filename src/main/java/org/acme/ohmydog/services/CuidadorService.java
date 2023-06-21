package org.acme.ohmydog.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.acme.ohmydog.repository.CuidadorRepository;
import org.acme.ohmydog.requests.CuidadorRequest;

@ApplicationScoped
public class CuidadorService {

    @Inject
    CuidadorRepository cuidadorRepository;

    @Transactional
    public boolean register(CuidadorRequest cuidadorRequest) {
        if (cuidadorRepository.buscarCuidadorPorEmail(cuidadorRequest.getEmail()) != null) {
            return false;
        }
        cuidadorRepository.register(cuidadorRequest.getNombre(), cuidadorRequest.getApellido(), cuidadorRequest.getDni(),
                cuidadorRequest.getTelefono(), cuidadorRequest.getEmail(), cuidadorRequest.getZona());
        return true;
    }
}
