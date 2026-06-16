package dev.webshop.categorieen;

import dev.webshop.artikelen.Artikel;
import jakarta.persistence.*;
import java.util.Set;
import static dev.webshop.utils.Check.*;

@Entity
@Table(name = "Categorieen")
public class Categorie {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long categorieId;
    private String naam;
    private Long hoofdCategorieId;
    @ManyToMany(mappedBy = "categorieen")
    Set<Artikel> artikelen;

    protected Categorie() {
    }

    public Categorie(Long categorieId, String naam, Long hoofdCategorieId) {
        checkNonNegativeOrZero(categorieId, "categorieId");
        checkNotBlank(naam, "Naam");

        this.categorieId = categorieId;
        this.naam = naam;
        this.hoofdCategorieId = hoofdCategorieId;
    }

    public Long getCategorieId() {
        return categorieId;
    }

    public String getNaam() {
        return naam;
    }

    public Long getHoofdCategorieId() {
        return hoofdCategorieId;
    }
}