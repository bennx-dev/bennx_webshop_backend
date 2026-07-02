package dev.webshop.klanten;

import dev.webshop.winkelmanden.Winkelmand;
import jakarta.persistence.*;

@Entity
@Table(name = "Klanten")
public class Klant {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long klantId;

    @OneToOne(mappedBy = "klant")
    private Winkelmand winkelmand;

    protected Klant() {

    }

    public Long getKlantId() {
        return klantId;
    }

    public Winkelmand getWinkelmand() {
        return winkelmand;
    }
}
