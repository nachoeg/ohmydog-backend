package org.acme.ohmydog.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.ohmydog.entities.Cuidador;

import java.util.List;

@ApplicationScoped
public class CuidadorRepository implements PanacheRepository<Cuidador> {

    public Cuidador buscarCuidadorPorEmail(String email) {
        return find("email", email).firstResult();
    }

    public Cuidador buscarCuidadorPorId(Long id) {
        return find("id", id).firstResult();
    }

    public void register(String nombre, String apellido, Long dni, Long telefono, String mail, String zona) {
        Cuidador paseador = new Cuidador(nombre, apellido, dni, telefono, mail, zona);
        persist(paseador);
    }

    public void eliminate(Cuidador cuidador) {
        delete(cuidador);
    }

    public List<Cuidador> listarCuidador() {
        return listAll();
    }
}
