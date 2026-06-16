package dev.webshop.categorieen;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorieRepository extends JpaRepository<Categorie, Long> {
    //is categorie een parent?
    boolean existsByHoofdCategorieId(long categorieId);

    boolean existsByCategorieId(long categorieId);
}