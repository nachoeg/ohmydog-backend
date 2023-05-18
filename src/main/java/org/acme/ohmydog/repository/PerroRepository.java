package org.acme.ohmydog.repository;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.ohmydog.entities.Perro;

import java.util.List;


@ApplicationScoped
public class PerroRepository implements PanacheRepository<Perro> {

    /**
     * Busca un perro por su id
     *
     * @param id
     * @return Perro
     */
    public Perro buscarPerroPorId(Long id) {
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

    public void eliminate(Perro perro) {
        delete(perro);
    }

}

