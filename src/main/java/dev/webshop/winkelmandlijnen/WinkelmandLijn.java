package dev.webshop.winkelmandlijnen;
import dev.webshop.artikelen.Artikel;
import dev.webshop.winkelmanden.Winkelmand;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "WinkelmandLijnen")
public class WinkelmandLijn {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long winkelmandLijnId;

    @Column(nullable = false)
    private Integer aantal;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "winkelmandId", nullable = false)
    private Winkelmand winkelmand;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "artikelId", nullable = false)
    private Artikel artikel;

    public WinkelmandLijn(Integer aantal, Winkelmand winkelmand, Artikel artikel) {

        if (aantal == null || aantal <= 0) {
            throw new IllegalArgumentException("Aantal moet positief zijn");
        }

        if (artikel == null) {
            throw new IllegalArgumentException("artikel is verplicht");
        }

        if (winkelmand == null) {
            throw new IllegalArgumentException("winkelmand is verplicht");
        }

        this.aantal = aantal;
        this.artikel = artikel;
        this.winkelmand = winkelmand;
    }

    protected WinkelmandLijn() {

    }

    public Long getWinkelmandLijnId() {
        return winkelmandLijnId;
    }

    public Integer getAantal() {
        return aantal;
    }

    public Winkelmand getWinkelmand() {
        return winkelmand;
    }

    public Artikel getArtikel() {
        return artikel;
    }

    public void verhoogAantalMet(Integer aantal) {
        if (aantal == null || aantal <= 0) {
            throw new IllegalArgumentException("Aantal moet positief zijn");
        }

        this.aantal += aantal;
    }

    //1 winkelmandId + artikelId is uniek in winkelmandLijn
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof WinkelmandLijn that)) {
            return false;
        }

        return Objects.equals(winkelmand, that.winkelmand)
                && Objects.equals(artikel, that.artikel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(winkelmand, artikel);
    }
}
