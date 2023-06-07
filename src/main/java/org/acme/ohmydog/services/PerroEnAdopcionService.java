package org.acme.ohmydog.services;
import jakarta.inject.Inject;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.acme.ohmydog.entities.Perro;
import org.acme.ohmydog.entities.PerroEnAdopcion;
import org.acme.ohmydog.repository.PerroEnAdopcionRepository;
import org.acme.ohmydog.repository.UsuarioRepository;
import org.acme.ohmydog.requests.PerroEnAdopcionRequest;
import org.acme.ohmydog.entities.Usuario;

import java.util.List;

@ApplicationScoped
public class PerroEnAdopcionService {

    @Inject
    PerroEnAdopcionRepository perroEnAdopcionRepository;

    @Inject
    UsuarioRepository usuarioRepository;

    @Transactional
    public boolean register(PerroEnAdopcionRequest perroEnAdopcionRequest) {
        Usuario usuario = usuarioRepository.buscarUsuarioPorId(perroEnAdopcionRequest.getIdUsuario());
        if (usuario == null) {
            return false;
        }
        PerroEnAdopcion perroEnAdopcion = perroEnAdopcionRepository.register(perroEnAdopcionRequest.getNombre(),
                perroEnAdopcionRequest.getRaza(), perroEnAdopcionRequest.getEdad(), perroEnAdopcionRequest.getSexo(),
                perroEnAdopcionRequest.getEmail(), perroEnAdopcionRequest.getTelefono());
        return true;
    }

    @Transactional
    public List<PerroEnAdopcion> listarPerrosEnAdopcion() {
        return perroEnAdopcionRepository.listarPerrosEnAdopcion();
    }

}