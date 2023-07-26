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
import org.acme.ohmydog.entities.Bouchers;
import org.acme.ohmydog.entities.Campania;
import org.acme.ohmydog.entities.Paseador;

import java.time.LocalDate;

@ApplicationScoped
public class MiApp {
        @PersistenceContext
        EntityManager entityManager;

        @Transactional
        void agregarUsuarios(@Observes StartupEvent event) {
                // Código para agregar usuarios a tu base de datos
                Usuario admin = new Usuario("admin@mail.com", "0", "admin", "admin", 0L, "Localidad0", "Direccion0",
                                123456789L,
                                "veterinario");
                Usuario usuario1 = new Usuario("usuario1@mail.com", "1", "Ignacio", "Escudero", 48521541L, "City Bell",
                                "455 esq. 24",
                                123456789L, "cliente");
                Usuario usuario2 = new Usuario("usuario2@mail.com", "2", "Jorge", "García", 28489524L, "La Plata",
                                "20 y 50",
                                223456789L, "cliente");
                Usuario usuario3 = new Usuario("usuario3@mail.com", "3", "Pedro", "Perez", 34789522L, "Gonnet",
                                "Centernario y 505",
                                323456789L, "cliente");

                // Persistir el usuario en la base de datos
                entityManager.persist(admin);
                entityManager.persist(usuario1);
                entityManager.persist(usuario2);
                entityManager.persist(usuario3);

                // Entidad necesaria para el sistema de bouchers.
                Bouchers boucher = new Bouchers();
                entityManager.persist(boucher);

                Perro perro1 = new Perro("Kala", "Giant Schnauzer", 6, "", "Hembra", "");
                Perro perro2 = new Perro("Lobo", "German Shepherd", 4, "", "Macho", "");
                Perro perro3 = new Perro("Titan", "Golden Retriever", 1, "", "Macho", "");
                entityManager.persist(perro1);
                entityManager.persist(perro2);
                usuario1.agregarPerro(perro1);
                usuario2.agregarPerro(perro2);
                usuario2.agregarPerro(perro3);

                // Agregar cuidador/paseador para pruebas:
                Paseador paseador = new Paseador("Hernesto", "Gomez", 42396050L, 2213037663L, "hernestogomez@mail.com",
                                "Los hornos",
                                "Paseador");
                Paseador cuidador = new Paseador("Pablo", "Perez", 42656045L, 2213067344L, "pabloperez@mail.com",
                                "Ensenada",
                                "Cuidador");
                entityManager.persist(paseador);
                entityManager.persist(cuidador);

                LocalDate fecha = LocalDate.of(2023, 7, 12);
                // Agregar campania pasada para pruebas
                Campania campania1 = new Campania("Campania", "Motivo", 1234L, 1234567L, "campania@mail.com", fecha,
                                fecha);
                entityManager.persist(campania1);

        }
}
