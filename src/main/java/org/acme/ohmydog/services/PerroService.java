package org.acme.ohmydog.services;

import jakarta.inject.Inject;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.acme.ohmydog.entities.Perro;
import org.acme.ohmydog.repository.PerroRepository;
import org.acme.ohmydog.repository.UsuarioRepository;
import org.acme.ohmydog.repository.TurnoRepository;
import org.acme.ohmydog.requests.PerroRequest;
import org.acme.ohmydog.entities.Usuario;
import org.acme.ohmydog.entities.Turno;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

@ApplicationScoped
public class PerroService {

    @Inject
    PerroRepository perroRepository;

    @Inject
    TurnoRepository turnoRepository;

    @Inject
    UsuarioRepository usuarioRepository;

    @Transactional
    public boolean register(PerroRequest perroRequest) {
        Usuario usuario = usuarioRepository.buscarUsuarioPorId(perroRequest.getIdUsuario());
        if (usuario == null) {
            return false;
        }
        List<Perro> perrosUsuario = perroRepository.listarPerrosCliente(usuario);
        String nombrePerro = perroRequest.getNombre();
        int contadorApariciones = 1;
        for (Perro p : perrosUsuario) {
            if (p.getNombre().startsWith(nombrePerro)) {
                contadorApariciones++;
            }
        }
        if (contadorApariciones > 1) {
            nombrePerro += " " + transformarARomano(contadorApariciones);
        }
        Perro perro = perroRepository.register(nombrePerro, perroRequest.getRaza(), perroRequest.getEdad(),
                perroRequest.getEnfermedad(), perroRequest.getSexo(), perroRequest.getCaracteristicas());
        usuario.agregarPerro(perro);
        return true;
    }

    private String transformarARomano(int x) {
        String[] unidades = { "", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX" };
        String[] decenas = { "", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC" };

        int unidad = x % 10;
        int decena = x / 10;

        return decenas[decena] + unidades[unidad];
    }

    @Transactional
    public boolean modificarPerro(Long id, String nombre, String raza, int edad, String enfermedad, String sexo,
            String caracteristicas, boolean castrado, LocalDate vacunaAntirrabica, LocalDate vacunaAntienfermedades) {
        Perro perro = perroRepository.buscarPerroPorId(id);
        if (perro == null) {
            return false;
        }

        String nombrePerro = nombre;
        // Si le puso el mismo nombre que no haga nada
        if (!perro.getNombre().equals(nombre)) {
            Usuario usuario = usuarioRepository.buscarUsuarioPorId(perro.getUsuarioId());
            if (usuario == null) {
                return false;
            }
            List<Perro> perrosUsuario = perroRepository.listarPerrosCliente(usuario);
            int contadorApariciones = 1;
            for (Perro p : perrosUsuario) {
                if (p.getNombre().startsWith(nombrePerro)) {
                    contadorApariciones++;
                }
            }
            if (contadorApariciones > 1) {
                nombrePerro += " " + transformarARomano(contadorApariciones);
            }
        }

        perro.setNombre(nombrePerro);
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

    // El borrado pasa a ser logico, ahora solo se cambia el booleano de los perros.
    @Transactional
    public boolean eliminarPerro(Long id) {
        Perro perro = perroRepository.buscarPerroPorId(id);
        if (perro == null) {
            return false;
        }
        // usuarioRepository.buscarUsuarioPorId(perro.getUsuarioId()).eliminarPerro(perro);
        // perroRepository.eliminate(perro);
        perro.setBorrado(true);
        List<Turno> turnos = perro.getTurnos();
        for (Turno t : turnos) {
            if (t.getEstado().equals("Pendiente")) {
                t.setEstado("Rechazado");
                turnoRepository.persist(t);
            }
        }
        perroRepository.persist(perro);
        return true;
    }

    // El borrado pasa a ser logico, ahora solo se cambia el booleano de los perros.
    @Transactional
    public boolean recuperarPerro(Long id) {
        Perro perro = perroRepository.buscarPerroPorId(id);
        if (perro == null) {
            return false;
        }
        // usuarioRepository.buscarUsuarioPorId(perro.getUsuarioId()).eliminarPerro(perro);
        // perroRepository.eliminate(perro);
        perro.setBorrado(false);
        perroRepository.persist(perro);
        return true;
    }

    // Ahora el listar perros y el listarPerros cliente filtran los perros que
    // tengan borrado = true
    @Transactional
    public List<Perro> listarPerros() {
        List<Perro> perros = perroRepository.listarPerros();
        List<Perro> perrosFiltrados = new ArrayList<>();
        for (Perro p : perros) {
            if (!p.getBorrado()) {
                perrosFiltrados.add(p);
            }
        }
        return perrosFiltrados;
    }

    // Ahora el listar perros y el listarPerros cliente filtran los perros que
    // tengan borrado = true
    @Transactional
    public List<Perro> listarPerrosBorrados() {
        List<Perro> perros = perroRepository.listarPerros();
        List<Perro> perrosFiltrados = new ArrayList<>();
        for (Perro p : perros) {
            if (p.getBorrado()) {
                perrosFiltrados.add(p);
            }
        }
        return perrosFiltrados;
    }

    @Transactional
    public List<Perro> listarPerrosCliente(Long id) {
        Usuario usuario = usuarioRepository.buscarUsuarioPorId(id);
        if (usuario == null) {
            return null;
        }
        List<Perro> perros = perroRepository.listarPerrosCliente(usuario);
        List<Perro> perrosFiltrados = new ArrayList<>();
        for (Perro p : perros) {
            if (!p.getBorrado()) {
                perrosFiltrados.add(p);
            }
        }
        return perrosFiltrados;
    }

    @Transactional
    public Perro buscarPerroPorId(Long id) {
        Perro perro = perroRepository.buscarPerroPorId(id);
        if (perro == null) {
            return null;
        }
        return perro;
    }

    @Transactional
    public boolean cruzar(Long id) {
        Perro perro = perroRepository.buscarPerroPorId(id);
        if (perro == null) {
            return false;
        }
        perro.setDisponibleCruza(!perro.getDisponibleCruza());
        perroRepository.persist(perro);
        return true;
    }

    @Transactional
    public List<Perro> getPerrosDisponibleParaCruza(Long id) {
        Usuario usuario = usuarioRepository.buscarUsuarioPorId(id);
        if (usuario == null) {
            return null;
        }
        List<Perro> perros = perroRepository.listarPerrosCliente(usuario);
        List<Perro> perrosDisponibles = new ArrayList<>();
        for (Perro p : perros) {
            if (p.getDisponibleCruza() && !p.getCastrado()) {
                perrosDisponibles.add(p);
            }
        }
        return perrosDisponibles;
    }

    @Transactional
    public List<Perro> getTodosLosPerrosDisponibleParaCruza() {
        List<Perro> perros = perroRepository.listarPerros();
        List<Perro> perrosDisponibles = new ArrayList<>();
        for (Perro p : perros) {
            if (p.getDisponibleCruza() && !p.getCastrado()) {
                perrosDisponibles.add(p);
            }
        }
        return perrosDisponibles;
    }

    // Este metodo lo que hace es:
    // Recibe un id del perro, y luego tiene que buscar
    // Los perros que tengan el sexo opuesto, es decir, los que no tengan el mismo
    // sexo que el
    // y los perros que tengan la misma raza.
    // En caso de que la raza sea "No especificada" entonces devuelve todos los
    // perros
    // que estan disponibles para cruzar.
    // Divido los IF para que sea mas legible.
    // No debe mostrar los perros que son del mismo duenio
    // Por lo que tambien filtra que el id del usuario sea distinto.
    @Transactional
    public List<Perro> getOpcionesCruza(Long id) {
        Perro perro = perroRepository.buscarPerroPorId(id);
        List<Perro> perros = perroRepository.listarPerros();
        List<Perro> perrosDisponibles = new ArrayList<>();
        for (Perro p : perros) {
            // Si esta disponible para cruzar, si esta castrado y si es del sexo opuesto se
            // pasa al segundo if
            if ((p.getUsuarioId() != perro.getUsuarioId()) && (p.getDisponibleCruza()) && (!p.getCastrado())
                    && !p.getSexo().equals(perro.getSexo())) {
                // Si la raza del perro que llega es no especificada o tienen la misma se agrega
                // a la lista
                if (perro.getRaza().equals("No especificada") || (p.getRaza().equals(perro.getRaza()))) {
                    perrosDisponibles.add(p);
                }
            }
        }
        return perrosDisponibles;
    }
}
