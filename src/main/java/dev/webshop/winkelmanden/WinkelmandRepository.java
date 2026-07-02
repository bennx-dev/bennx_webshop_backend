package dev.webshop.winkelmanden;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WinkelmandRepository extends JpaRepository<Winkelmand, Long> {

    @EntityGraph(attributePaths = {"winkelmandLijnen", "winkelmandLijnen.artikel"})
    Optional<Winkelmand> findBySessieId(String sessieId);

    @EntityGraph(attributePaths = {"winkelmandLijnen", "winkelmandLijnen.artikel"})
    Optional<Winkelmand> findByKlantKlantId(Long klantId);
}
