package org.acme.ohmydog.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.ohmydog.entities.Perro;
import org.acme.ohmydog.entities.PerroEnAdopcion;

import java.util.List;

@ApplicationScoped
public class PerroEnAdopcionRepository implements PanacheRepository<PerroEnAdopcion> {

    public PerroEnAdopcion register(String nombre, String raza, int edad, String sexo, String email, Long telefono, String caracteristicas, String enfermedades) {
        PerroEnAdopcion perroEnAdopcion = new PerroEnAdopcion(nombre, raza, edad, sexo, email, telefono, caracteristicas, enfermedades);
        persist(perroEnAdopcion);
        return perroEnAdopcion;
    }

    public List<PerroEnAdopcion> listarPerrosEnAdopcion() {
        return listAll();
    }

}
