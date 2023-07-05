package org.acme.ohmydog.services;

import jakarta.inject.Inject;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.acme.ohmydog.entities.Perro;
import org.acme.ohmydog.excepciones.ExcepcionCastrado;
import org.acme.ohmydog.excepciones.ExcepcionNombrePerro;
import org.acme.ohmydog.repository.PerroRepository;
import org.acme.ohmydog.repository.UsuarioRepository;
import org.acme.ohmydog.requests.PerroRequest;
import org.acme.ohmydog.entities.Usuario;

import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class PerroService {

    @Inject
    PerroRepository perroRepository;

    @Inject
    UsuarioRepository usuarioRepository;

    @Transactional
    public boolean register(PerroRequest perroRequest) throws ExcepcionNombrePerro {
        Usuario usuario = usuarioRepository.buscarUsuarioPorId(perroRequest.getIdUsuario());
        if (usuario == null) {
            return false;
        }
        if (usuario.coincideNombrePerro(perroRequest.getNombre())) {
            throw new ExcepcionNombrePerro("El perro no puede registrarse ya que el usuario tiene otro perro " +
                    "con el mismo nombre.");
        }
        Perro perro = perroRepository.register(perroRequest.getNombre(), perroRequest.getRaza(), perroRequest.getEdad(),
                perroRequest.getEnfermedad(), perroRequest.getSexo(), perroRequest.getCaracteristicas());
        usuario.agregarPerro(perro);
        return true;
    }

    @Transactional
    public boolean modificarPerro(Long id, String nombre, String raza, int edad, String enfermedad, String sexo,
            String caracteristicas, boolean castrado, LocalDate vacunaAntirrabica, LocalDate vacunaAntienfermedades) {
        Perro perro = perroRepository.buscarPerroPorId(id);
        if (perro == null) {
            return false;
        }
        perro.setNombre(nombre);
        perro.setRaza(raza);
        perro.setEdad(edad);
        perro.setEnfermedad(enfermedad);
        perro.setSexo(sexo);
        perro.setCaracteristicas(caracteristicas);
        perro.setCastrado(castrado);
        perro.setVacunaAntienfermedades(vacunaAntienfermedades);
        perro.setVacunaAntirrabica(vacunaAntirrabica);
        perroRepository.persist(perro);
        return true;
    }

    @Transactional
    public boolean eliminarPerro(Long id) {
        Perro perro = perroRepository.buscarPerroPorId(id);
        if (perro == null) {
            return false;
        }
        usuarioRepository.buscarUsuarioPorId(perro.getUsuarioId()).eliminarPerro(perro);
        perroRepository.eliminate(perro);
        return true;
    }

    @Transactional
    public List<Perro> listarPerros() {
        return perroRepository.listarPerros();
    }

    @Transactional
    public List<Perro> listarPerrosCliente(Long id) {
        Usuario usuario = usuarioRepository.buscarUsuarioPorId(id);
        if (usuario == null) {
            return null;
        }
        return perroRepository.listarPerrosCliente(usuario);
    }

    @Transactional
    public Perro buscarPerroPorId(Long id) {
        Perro perro = perroRepository.buscarPerroPorId(id);
        if (perro == null) {
            return null;
        }
        return perro;
    }

}
