package org.acme.ohmydog.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.acme.ohmydog.entities.PerroPerdido;
import org.acme.ohmydog.repository.PerroPerdidoRepository;
import org.acme.ohmydog.requests.PerroPerdidoRequest;
import java.util.List;

@ApplicationScoped
public class PerroPerdidoService {

    @Inject
    PerroPerdidoRepository perroPerdidoRepository;

    @Transactional
    public boolean register(PerroPerdidoRequest perroPerdidoRequest) {
        PerroPerdido perroPerdido = perroPerdidoRepository.register(perroPerdidoRequest.getIdUsuario(),perroPerdidoRequest.getNombre(), perroPerdidoRequest.getZona(), perroPerdidoRequest.getFecha(),
        perroPerdidoRequest.getEmail());
        return true;
    }

    @Transactional
    public boolean modificarPerroPerdido(Long id, PerroPerdidoRequest perroPerdidoRequest) {
        PerroPerdido perroPerdido = perroPerdidoRepository.buscarPerroPorId(id);
        if (perroPerdido == null) {
            return false;
        }
        perroPerdido.setNombre(perroPerdidoRequest.getNombre());
        perroPerdido.setZona(perroPerdidoRequest.getZona());
        perroPerdido.setFecha(perroPerdidoRequest.getFecha());
        perroPerdido.setEmail(perroPerdidoRequest.getEmail());
        perroPerdidoRepository.persist(perroPerdido);
        return true;
    }

    @Transactional
    public boolean eliminarPerroPerdido(Long id) {
        PerroPerdido perroPerdido = perroPerdidoRepository.buscarPerroPorId(id);
        if (perroPerdido == null) {
            return false;
        }
        perroPerdidoRepository.eliminate(perroPerdido);
        return true;
    }

    @Transactional
    public List<PerroPerdido> listarPerrosPerdidos() {
        return perroPerdidoRepository.listarPerrosPerdidos();
    }
}
