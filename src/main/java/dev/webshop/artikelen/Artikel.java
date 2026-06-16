package dev.webshop.artikelen;
import static dev.webshop.utils.Check.*;

import dev.webshop.categorieen.Categorie;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "Artikelen")
public class Artikel {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long artikelId;
    private String ean;
    private String naam;
    private String beschrijving;
    private BigDecimal prijs;
    private Long gewichtInGram;
    private Long bestelpeil;
    private Long voorraad;
    private Long minimumVoorraad;
    private Long maximumVoorraad;
    private Long levertijd;
    private Long aantalBesteldLeverancier;
    private Long maxAantalInMagazijnPLaats;
    private Long leveranciersId;
    @ManyToMany
    @JoinTable(
            name = "ArtikelCategorieen",
            joinColumns = @JoinColumn(name = "artikelId"),
            inverseJoinColumns = @JoinColumn(name = "categorieId")
    )
    private Set<Categorie> categorieen;

    protected Artikel() {
    }

    public Artikel(Long artikelId, String ean, String naam, String beschrijving, BigDecimal prijs, Long gewichtInGram, Long bestelpeil, Long voorraad, Long minimumVoorraad, Long maximumVoorraad, Long levertijd, Long aantalBesteldLeverancier, Long maxAantalInMagazijnPLaats, Long leveranciersId) {
        checkNotBlank(ean, "EAN");
        checkNotBlank(naam, "Naam");
        checkNonNegative(prijs, "Prijs");
        checkNonNegative(gewichtInGram, "Gewicht");
        checkNonNegative(bestelpeil, "Bestelpeil");
        checkNonNegative(voorraad, "Voorraad");
        checkNonNegative(minimumVoorraad, "Minimum voorraad");
        checkNonNegative(maximumVoorraad, "Maximum voorraad");
        checkNonNegative(levertijd, "Levertijd");
        checkNonNegative(aantalBesteldLeverancier, "Aantal besteld leverancier");
        checkNonNegative(maxAantalInMagazijnPLaats, "Maximum in magazijn");
        checkNonNegativeOrZero(leveranciersId, "Leveranciers ID");

        this.artikelId = artikelId;
        this.ean = ean;
        this.naam = naam;
        this.beschrijving = beschrijving;
        this.prijs = prijs;
        this.gewichtInGram = gewichtInGram;
        this.bestelpeil = bestelpeil;
        this.voorraad = voorraad;
        this.minimumVoorraad = minimumVoorraad;
        this.maximumVoorraad = maximumVoorraad;
        this.levertijd = levertijd;
        this.aantalBesteldLeverancier = aantalBesteldLeverancier;
        this.maxAantalInMagazijnPLaats = maxAantalInMagazijnPLaats;
        this.leveranciersId = leveranciersId;
    }

    public Long getArtikelId() {
        return artikelId;
    }

    public String getEan() {
        return ean;
    }

    public String getNaam() {
        return naam;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public BigDecimal getPrijs() {
        return prijs;
    }

    public Long getGewichtInGram() {
        return gewichtInGram;
    }

    public Long getBestelpeil() {
        return bestelpeil;
    }

    public Long getVoorraad() {
        return voorraad;
    }

    public Long getMinimumVoorraad() {
        return minimumVoorraad;
    }

    public Long getMaximumVoorraad() {
        return maximumVoorraad;
    }

    public Long getLevertijd() {
        return levertijd;
    }

    public Long getAantalBesteldLeverancier() {
        return aantalBesteldLeverancier;
    }

    public Long getMaxAantalInMagazijnPLaats() {
        return maxAantalInMagazijnPLaats;
    }

    public Long getLeveranciersId() {
        return leveranciersId;
    }

    public Set<Categorie> getCategorieen() {
        return categorieen;
    }
}