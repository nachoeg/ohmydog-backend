package org.acme.ohmydog.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.ohmydog.entities.Guarderia;
import org.acme.ohmydog.entities.Guarderia;

import java.util.List;

@ApplicationScoped
public class GuarderiaRepository implements PanacheRepository<Guarderia> {

    public Guarderia buscarGuarderiaPorEmail(String email) {
        return find("email", email).firstResult();
    }

    public Guarderia buscarGuarderiaPorId(Long id) {
        return find("id", id).firstResult();
    }

    public void register(String nombre, String disponibilidad, Long telefono, String email, String localidad, String direccion) {
        Guarderia guarderia = new Guarderia(nombre, disponibilidad, telefono, email, localidad, direccion);
        persist(guarderia);
    }

    public void eliminate(Guarderia guarderia) {
        delete(guarderia);
    }

    public List<Guarderia> listarGuarderia() {
        return listAll();
    }
}
