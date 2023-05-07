package org.acme.ohmydog;

import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.acme.ohmydog.entities.Usuario;

@ApplicationScoped
public class MiApp {
    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    void agregarUsuarios(@Observes StartupEvent event) {
        // Código para agregar usuarios a tu base de datos
        Usuario usuario1 = new Usuario("usuario1@mail.com", "123", "usuario1", "1", 1L, "Localidad1", "Direccion1", 123456789L);
        Usuario usuario2 = new Usuario("usuario2@mail.com", "223", "usuario2", "2", 2L, "Localidad2", "Direccion2", 123456789L);
        Usuario usuario3 = new Usuario("usuario3@mail.com", "323", "usuario3", "3", 3L, "Localidad3", "Direccion3", 123456789L);

        // Persistir el usuario en la base de datos
        entityManager.persist(usuario1);
        entityManager.persist(usuario2);
        entityManager.persist(usuario3);
    }
}
