package org.acme.ohmydog.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.ohmydog.entities.PerroPerdido;

import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class PerroPerdidoRepository implements PanacheRepository<PerroPerdido> {

    public PerroPerdido register(long idUsuario, String nombre,  String zona, LocalDate fecha, String email) {
        PerroPerdido perroPerdido = new PerroPerdido(idUsuario, nombre, zona, fecha, email);
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
