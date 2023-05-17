package org.acme.ohmydog.services;

import jakarta.inject.Inject;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.acme.ohmydog.entities.Turno;
import org.acme.ohmydog.entities.Usuario;
import org.acme.ohmydog.repository.UsuarioRepository;
import org.acme.ohmydog.requests.UsuarioRequest;


import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped // Asegura que el objeto se inicialice solo una vez y se reutilice en toda la aplicacion
public class UsuarioService {

    @Inject
    UsuarioRepository usuarioRepository;

    /**
     * Registra un nuevo usuario en la base de datos con los datos proporcionados
     * @param usuarioRequest
     * @return boolean
     */
    @Transactional
    public boolean register(UsuarioRequest usuarioRequest) {
        if (usuarioRepository.buscarUsuarioPorEmail(usuarioRequest.getEmail()) != null) { // Verificar si el email ya existe en la base de datos
            return false; // Email ya existe
        }
        usuarioRepository.register(usuarioRequest.getEmail(), usuarioRequest.getPassword(),
                usuarioRequest.getNombre(), usuarioRequest.getApellido(), usuarioRequest.getDni(),
                usuarioRequest.getLocalidad(), usuarioRequest.getDireccion(), usuarioRequest.getTelefono(), usuarioRequest.getRol()); // Registra el nuevo usuario en la base de datos
        return true;
    }

    /**
     * Recibe los parámetros para modificar un usuario ya existente en la base de datos.
     * @param email
     * @param localidad
     * @param direccion
     * @param telefono
     * @return
     */
    @Transactional
    public boolean modificarUsuario(Long dni, String email, String localidad, String direccion, Long telefono) {
        Usuario usuario = usuarioRepository.buscarUsuarioPorDni(dni);
        if ((usuario == null) || (usuarioRepository.verificarEmail(email))) {
            return false; // No se encontró el usuario con el dni especificado o el email dado ya existe en la base de datos.
        }
        usuario.setEmail(email);
        usuario.setLocalidad(localidad);
        usuario.setDireccion(direccion);
        usuario.setTelefono(telefono);
        usuarioRepository.persist(usuario); // Actualizar el usuario en la base de datos
        return true;
    }

    /**
     * Busca en la base de datos el usuario correspondiente al ID proporcionado como parametro. Si se encuentra, se llama al metodo "eliminate" del
     * "usuarioRepository" para eliminarlo de la base de datos.
     * @param id
     * @return
     */
    @Transactional
    public boolean eliminarUsuario(Long id) {
        Usuario usuario = usuarioRepository.buscarUsuarioPorId(id);
        if (usuario == null) {
            return false; // No se encontró el usuario con el id especificado
        }
        usuarioRepository.eliminate(usuario); // Eliminar el usuario de la base de datos
        return true;
    }

    /**
     * Recupera todos los usuarios de la base de datos y los devuelve como una lista.
     *
     * @return Lista de usuarios
     */
    @Transactional
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.listarUsuarios();
    }

//    @Transactional
//    public List<Turno> listarTurnos(Usuario usuario) {
//        return usuario.getTurnos();
//    }
}
