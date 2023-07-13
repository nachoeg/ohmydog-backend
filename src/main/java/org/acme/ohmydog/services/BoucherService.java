
package org.acme.ohmydog.services;

import jakarta.inject.Inject;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.acme.ohmydog.entities.Bouchers;

import org.acme.ohmydog.repository.BoucherRepository;

@ApplicationScoped // Asegura que el objeto se inicialice solo una vez y se reutilice en toda la
                   // aplicacion
public class BoucherService {

    @Inject
    BoucherRepository boucherRepository;

    @Transactional
    public boolean quemarBoucher(Long dni) {
        return boucherRepository.quemarBoucher(dni);
    }

    @Transactional
    public boolean agregarBoucher(Long dni) {
        return boucherRepository.agregarBoucher(dni);
    }

}
