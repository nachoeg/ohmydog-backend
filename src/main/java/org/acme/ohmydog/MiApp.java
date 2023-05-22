package org.acme.ohmydog;

import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.acme.ohmydog.entities.Perro;
import org.acme.ohmydog.entities.Turno;
import org.acme.ohmydog.entities.Usuario;

import java.time.LocalDate;

@ApplicationScoped
public class MiApp {
    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    void agregarUsuarios(@Observes StartupEvent event) {
        // Código para agregar usuarios a tu base de datos
        Usuario admin = new Usuario("admin@mail.com", "0", "admin", "admin", 0L, "Localidad0", "Direccion0", 123456789L, "veterinario");

        // Persistir el usuario en la base de datos
        entityManager.persist(admin);

    }
}
