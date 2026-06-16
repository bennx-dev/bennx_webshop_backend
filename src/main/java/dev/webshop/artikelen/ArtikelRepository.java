package dev.webshop.artikelen;

import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtikelRepository extends JpaRepository<Artikel, Long> {
    @EntityGraph(attributePaths = "categorieen")
    Page<Artikel> findAll(@NonNull Pageable pageable);

    @EntityGraph(attributePaths = "categorieen")
    Page<Artikel> findByCategorieenCategorieId(long categorieId, Pageable pageable);

}