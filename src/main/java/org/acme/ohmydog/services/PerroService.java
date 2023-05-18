package org.acme.ohmydog.services;
import jakarta.inject.Inject;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.acme.ohmydog.entities.Perro;
import org.acme.ohmydog.repository.PerroRepository;
import org.acme.ohmydog.repository.UsuarioRepository;
import org.acme.ohmydog.requests.PerroRequest;
import org.acme.ohmydog.entities.Usuario;

import java.util.List;

@ApplicationScoped
public class PerroService {

    @Inject
    PerroRepository perroRepository;

    @Inject
    UsuarioRepository usuarioRepository;

    @Transactional
    public boolean register(PerroRequest perroRequest) {
        Usuario usuario = usuarioRepository.buscarUsuarioPorId(perroRequest.getIdUsuario());
        if (usuario == null) {
            return false;
        }
        Perro perro = perroRepository.register(perroRequest.getNombre(), perroRequest.getRaza(), perroRequest.getEdad(),
                perroRequest.getEnfermedad(), perroRequest.getSexo(), perroRequest.getCaracteristicas());
        usuario.agregarPerro(perro);
        return true;
    }

    @Transactional
    public List<Perro> listarPerros() {
        return perroRepository.listarPerros();
    }

}
//
//    /**
//     * Recibe los parámetros para modificar un perro ya existente en la base de datos.
//     * @param nombre
//     * @param raza
//     * @param edad
//     * @param enfermedad
//     * @param sexo
//     * @param caracteristicas
//     * @return boolean
//     */
//
//    @Transactional
//    public boolean modificarPerro(Long id, String nombre, String raza, int edad, String enfermedad,
//    char sexo, String caracteristicas) {
//        Perro perro = perroRepository.buscarPerroPorID(id);
//        if (perro == null) {
//            return false; // No se encontró el perro especificado
//        }
//        perro.setNombre(nombre);
//        perro.setRaza(raza);
//        perro.setEdad(edad);
//        perro.setEnfermedad(enfermedad);
//        perro.setSexo(sexo);
//        perro.setCaracteristicas(caracteristicas);
//        perroRepository.persist(perro); // Actualizar el perro en la base de datos
//        return true;
//    }
//
//    /**
//     * Busca en la base de datos el perro correspondiente. Si se encuentra, se llama al metodo "eliminate" del
//     * "perroRepository" para eliminarlo de la base de datos.
//     * @param id
//     * @return boolean
//     */
//    @Transactional
//    public boolean eliminarPerro(Long id, Usuario usuario) {
//        Perro perro = perroRepository.buscarPerroPorID(id);
//        if (perro == null) {
//            return false; // No se encontró el perro
//        }
//        perroRepository.eliminate(perro); // Eliminar el perro de la base de datos
//        return true;
//    }
//
//}

