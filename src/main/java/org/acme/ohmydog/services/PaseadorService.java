package org.acme.ohmydog.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.acme.ohmydog.entities.Paseador;
import org.acme.ohmydog.repository.PaseadorRepository;
import org.acme.ohmydog.requests.PaseadorRequest;

import java.util.List;

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

    @Transactional
    public boolean modificarPaseador(Long id, PaseadorRequest paseadorRequest) {
        Paseador paseador = paseadorRepository.buscarPaseadorPorId(id);
        if (paseador == null) {
            return false;
        }
        paseador.setNombre(paseadorRequest.getNombre());
        paseador.setApellido(paseadorRequest.getApellido());
        paseador.setDni(paseadorRequest.getDni());
        paseador.setTelefono(paseadorRequest.getTelefono());
        paseador.setEmail(paseadorRequest.getEmail());
        paseador.setZona(paseadorRequest.getZona());
        paseadorRepository.persist(paseador);
        return true;
    }

    @Transactional
    public boolean eliminarPaseador(Long id) {
        Paseador paseador = paseadorRepository.buscarPaseadorPorId(id);
        if (paseador == null) {
            return false;
        }
        paseadorRepository.eliminate(paseador);
        return true;
    }

    @Transactional
    public List<Paseador> listarPaseador() {
        return paseadorRepository.listarPaseador();
    }
}
