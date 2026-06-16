package dev.webshop;

import dev.webshop.categorieen.Categorie;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CategorieTest {

    /*
    ================ EDGE TESTS =================
    */

    @Test
    @DisplayName("Een lege naam is niet geldig en werpt een exception")
    void eenLegeNaamisNietGeldig () {

        assertThrows(IllegalArgumentException.class, () ->
                new Categorie(1L,"", null));
    }

    @Test
    @DisplayName("Een negatieve categorieId is niet geldig en werpt een exception")
    void eenNegatieveCategorieIdIsNietGeldig () {

        assertThrows(IllegalArgumentException.class, () ->
                new Categorie(-1L,"test", 30L));
    }

    /*
    ================ EXPECTED BEHAVIOR TESTS =================
    */

    @Test
    @DisplayName("Een hoofd categorie is correct")
    void hoofdCategorieCorrect() {
        Categorie categorie = new Categorie(1L, "test", null);

        assertNull(categorie.getHoofdCategorieId());
        assertEquals("test", categorie.getNaam());
    }
}