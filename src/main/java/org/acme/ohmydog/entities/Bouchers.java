package org.acme.ohmydog.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "BOUCHERS")
public class Bouchers extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "dnis")
    private List<Long> dnis;

    public Bouchers() {
        this.dnis = new ArrayList<Long>();
    }

    public boolean quemarBoucher(Long dni) {
        if (this.existeBoucher(dni)) {
            this.dnis.remove(dni);
            return true;
        }
        return false;
    }

    public boolean agregarBoucher(Long dni) {
        this.dnis.add(dni);
        return true;
    }

    public boolean existeBoucher(Long dni) {
        return this.dnis.contains(dni);
    }
}
