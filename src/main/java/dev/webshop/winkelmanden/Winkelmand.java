package dev.webshop.winkelmanden;
import dev.webshop.artikelen.Artikel;
import dev.webshop.klanten.Klant;
import dev.webshop.winkelmandlijnen.WinkelmandLijn;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Winkelmanden")
public class Winkelmand {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long winkelmandId;

    //sessieId == gast
    private String sessieId;

    @Column(nullable = false)
    private LocalDateTime laatsteActiviteit;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "klantId", unique = true)
    private Klant klant;

    @OneToMany(mappedBy = "winkelmand", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<WinkelmandLijn> winkelmandLijnen = new HashSet<>();

    public static Winkelmand voorGast(String sessieId) {
        return new Winkelmand(sessieId, null);
    }

    public static Winkelmand voorKlant(Klant klant) {
        return new Winkelmand(null, klant);
    }

    private Winkelmand(String sessieId, Klant klant) {

        if (sessieId != null && sessieId.isBlank()) {
            throw new IllegalArgumentException("sessieId mag niet leeg zijn");
        }

        if (sessieId == null && klant == null) {
            throw new IllegalArgumentException("sessieId of klant is verplicht");
        }

        if (sessieId != null && klant != null) {
            throw new IllegalArgumentException("Winkelmand mag niet tegelijk sessieId en klant hebben");
        }

        this.sessieId = sessieId;
        this.klant = klant;
        this.laatsteActiviteit = LocalDateTime.now();
    }

    protected Winkelmand() {

    }

    public Long getWinkelmandId() {
        return winkelmandId;
    }

    public String getSessieId() {
        return sessieId;
    }

    public LocalDateTime getLaatsteActiviteit() {
        return laatsteActiviteit;
    }

    public Klant getKlant() {
        return klant;
    }

    public Set<WinkelmandLijn> getWinkelmandLijnen() {
        return winkelmandLijnen;
    }

    public void voegLijnToe(Artikel artikel, Integer aantal) {
        var nieuweLijn = new WinkelmandLijn(aantal, this, artikel);

        for (var lijn : winkelmandLijnen) {
            if (lijn.equals(nieuweLijn)) {
                lijn.verhoogAantalMet(aantal);
                return;
            }
        }

        winkelmandLijnen.add(nieuweLijn);
    }

}
