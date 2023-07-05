package org.acme.ohmydog.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.acme.ohmydog.entities.Cuidador;
import org.acme.ohmydog.repository.CuidadorRepository;
import org.acme.ohmydog.requests.CuidadorRequest;

import java.util.List;

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

    @Transactional
    public boolean modificarCuidador(Long id, CuidadorRequest cuidadorRequest) {
        Cuidador cuidador = cuidadorRepository.buscarCuidadorPorId(id);
        if (cuidador == null) {
            return false;
        }
        cuidador.setNombre(cuidadorRequest.getNombre());
        cuidador.setApellido(cuidadorRequest.getApellido());
        cuidador.setDni(cuidadorRequest.getDni());
        cuidador.setTelefono(cuidadorRequest.getTelefono());
        cuidador.setEmail(cuidadorRequest.getEmail());
        cuidador.setZona(cuidadorRequest.getZona());
        cuidador.setEstado(cuidadorRequest.getEstado());
        cuidadorRepository.persist(cuidador);
        return true;
    }

    @Transactional
    public boolean eliminarCuidador(Long id) {
        Cuidador cuidador = cuidadorRepository.buscarCuidadorPorId(id);
        if (cuidador == null) {
            return false;
        }
        cuidadorRepository.eliminate(cuidador);
        return true;
    }

    @Transactional
    public List<Cuidador> listarCuidador() {
        return cuidadorRepository.listarCuidador();
    }
}
