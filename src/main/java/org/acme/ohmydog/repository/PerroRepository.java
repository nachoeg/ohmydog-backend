package org.acme.ohmydog.repository;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.ohmydog.entities.Perro;
import org.acme.ohmydog.entities.Usuario;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@ApplicationScoped
public class PerroRepository implements PanacheRepository<Perro> {

    /**
     * Busca un perro por su id
     *
     * @param id
     * @return Perro
     */
    public Perro buscarPerroPorID(Long id) {
        return find("id", id).firstResult();
    }

    public Perro register(String nombre, String raza, int edad, String enfermedad, String sexo, String caracteristicas) {
        Perro perro = new Perro(nombre, raza, edad, enfermedad, sexo, caracteristicas);
        persist(perro);
        return perro;
    }

    public List<Perro> listarPerros() {
        return listAll();
    }

}
//
//    /**
//     * Se encarga de eliminar un objeto Perro especifico de la base de datos utilizando el método delete() del repositorio de perro
//     * @param perro
//     */
//    public void eliminate(Perro perro) {
//        delete(perro); // Elimina el perro de la BD
//    }
//}

