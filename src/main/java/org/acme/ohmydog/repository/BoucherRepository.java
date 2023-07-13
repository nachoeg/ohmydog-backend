package org.acme.ohmydog.repository;

import org.acme.ohmydog.entities.Bouchers;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BoucherRepository implements PanacheRepository<Bouchers> {

    public boolean quemarBoucher(Long dni) {
        // Toma el objeto boucher
        Bouchers boucher = find("id", 1).firstResult();
        if (boucher.quemarBoucher(dni)) {
            persist(boucher);
            return true;
        }
        return false;
    }

    public boolean agregarBoucher(Long dni) {
        // Toma el objeto boucher
        Bouchers boucher = find("id", 1).firstResult();
        boucher.agregarBoucher(dni);
        persist(boucher);
        return true;
    }

    public boolean existeBoucher(Long dni) {
        // Toma el objeto boucher
        Bouchers boucher = find("id", 1).firstResult();
        return boucher.existeBoucher(dni);
    }
}
