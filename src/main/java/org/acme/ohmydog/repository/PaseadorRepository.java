package org.acme.ohmydog.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.ohmydog.entities.Paseador;

@ApplicationScoped
public class PaseadorRepository implements PanacheRepository<Paseador> {

    public Paseador buscarPaseadorPorEmail(String email) {
        return find("email", email).firstResult();
    }

    public void register(String nombre, String apellido, Long dni, Long telefono, String mail, String zona) {
        Paseador paseador = new Paseador(nombre, apellido, dni, telefono, mail, zona);
        persist(paseador);
    }
}
