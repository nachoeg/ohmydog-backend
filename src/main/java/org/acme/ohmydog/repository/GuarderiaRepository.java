package org.acme.ohmydog.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.ohmydog.entities.Guarderia;

@ApplicationScoped
public class GuarderiaRepository implements PanacheRepository<Guarderia> {

    public Guarderia buscarGuarderiaPorEmail(String email) {
        return find("email", email).firstResult();
    }

    public void register(String nombre, String disponibilidad, Long telefono, String email, String localidad, String direccion) {
        Guarderia guarderia = new Guarderia(nombre, disponibilidad, telefono, email, localidad, direccion);
        persist(guarderia);
    }
}
