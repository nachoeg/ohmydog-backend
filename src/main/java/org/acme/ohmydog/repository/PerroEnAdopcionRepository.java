package org.acme.ohmydog.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.ohmydog.entities.PerroEnAdopcion;

import java.util.List;

@ApplicationScoped
public class PerroEnAdopcionRepository implements PanacheRepository<PerroEnAdopcion> {

    public PerroEnAdopcion register(Long idUsuario, String nombre, String raza, int edad, String sexo, String email, Long telefono, String caracteristicas, String descripcion) {
        PerroEnAdopcion perroEnAdopcion = new PerroEnAdopcion(idUsuario, nombre, raza, edad, sexo, email, telefono, caracteristicas, descripcion);
        persist(perroEnAdopcion);
        return perroEnAdopcion;
    }

    public List<PerroEnAdopcion> listarPerrosEnAdopcion() {
        return listAll();
    }

    public PerroEnAdopcion buscarPerroPorId(Long id) {
        return find("id", id).firstResult();
    }

    public void eliminate(PerroEnAdopcion perroEnAdopcion) {
        delete(perroEnAdopcion);
    }

}
