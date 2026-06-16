package dev.webshop;

import dev.webshop.artikelen.Artikel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

public class ArtikelTest {

    /*
    ================ EDGE TESTS =================
    */

    @Test
    @DisplayName("Een lege ean is niet geldig en werpt een exception")
    void eenLegeEanisNietGeldig () {

        assertThrows(IllegalArgumentException.class, () ->
                new Artikel(1L, "", "Naam"
                        , "Beschrijving",
                        BigDecimal.TEN, 100L, 10L
                        , 50L, 5L, 100L
                        , 2L, 0L, 50L
                        , 1L));
    }

    @Test
    @DisplayName("Een prijs onder 0 is niet geldig en werpt een exception")
    void eenPrijsOnderNulisNietGeldig () {
        assertThrows(IllegalArgumentException.class, () ->
                new Artikel(1L, "5499999000019", "Naam"
                        , "Beschrijving",
                        BigDecimal.valueOf(-1), 100L, 10L
                        , 50L, 5L, 100L
                        , 2L, 0L, 50L
                        , 1L));
    }

    @Test
    @DisplayName("Een leveranciersId met als waarde 0 is niet geldig en werpt een exception")
    void eenLeveranciersIdMetWaarde0IsNietGeldig () {
        assertThrows(IllegalArgumentException.class, () ->
                new Artikel(1L, "5499999000019", "Naam"
                        , "Beschrijving",
                        BigDecimal.TEN, 100L, 10L
                        , 50L, 5L, 100L
                        , 2L, 0L, 50L
                        , 0L));
    }
}