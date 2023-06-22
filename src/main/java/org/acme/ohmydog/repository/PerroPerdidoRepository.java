package org.acme.ohmydog.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.ohmydog.entities.PerroPerdido;

import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class PerroPerdidoRepository implements PanacheRepository<PerroPerdido> {

    public PerroPerdido register(String nombre, String raza, String zona, LocalDate fecha, String email) {
        PerroPerdido perroPerdido = new PerroPerdido(nombre, raza, zona, fecha, email);
        persist(perroPerdido);
        return perroPerdido;
    }

    public PerroPerdido buscarPerroPorId(Long id) {
        return find("id", id).firstResult();
    }

    public void eliminate(PerroPerdido perroEnAdopcion) {
        delete(perroEnAdopcion);
    }

    public List<PerroPerdido> listarPerrosPerdidos() {
        return listAll();
    }
}
