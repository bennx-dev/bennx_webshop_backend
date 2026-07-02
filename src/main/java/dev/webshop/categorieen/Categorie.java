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

    public Categorie(String naam, Long hoofdCategorieId) {
        checkNotBlank(naam, "Naam");

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