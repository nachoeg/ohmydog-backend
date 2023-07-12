package org.acme.ohmydog.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.ohmydog.entities.Paseador;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class PaseadorRepository implements PanacheRepository<Paseador> {

    public Paseador buscarPaseadorPorEmail(String email) {
        return find("email", email).firstResult();
    }

    public Paseador buscarPaseadorPorId(Long id) {
        return find("id", id).firstResult();
    }

    public void register(String nombre, String apellido, Long dni, Long telefono, String mail, String zona,
            String tipo) {
        Paseador paseador = new Paseador(nombre, apellido, dni, telefono, mail, zona, tipo);
        persist(paseador);
    }

    public void eliminate(Paseador paseador) {
        paseador.setBorrado(true);
        persist(paseador);
    }

    public List<Paseador> listarPaseador() {
        List<Paseador> paseadores = listAll();
        List<Paseador> paseadoresNoBorrados = new ArrayList<>();

        for (Paseador paseador : paseadores) {
            if (!paseador.getBorrado() && paseador.getEstado()) {
                paseadoresNoBorrados.add(paseador);
            }
        }

        return paseadoresNoBorrados;
    }

    public List<Paseador> listarPaseadoresBorrados() {
        List<Paseador> paseadores = listAll();
        List<Paseador> paseadoresBorrados = new ArrayList<>();

        for (Paseador paseador : paseadores) {
            if (paseador.getBorrado() || !paseador.getEstado()) {
                paseadoresBorrados.add(paseador);
            }
        }

        return paseadoresBorrados;
    }

    public boolean recuperar(Long id) {
        Paseador paseador = buscarPaseadorPorId(id);
        if (paseador == null) {
            return false;
        }
        paseador.setBorrado(false);
        paseador.setEstado(true);
        persist(paseador);
        return true;
    }
}
