package dev.webshop.artikelen;
import java.math.BigDecimal;
import java.util.List;

public record ArtikelDto(
        Long artikelId,
        String naam,
        String beschrijving,
        BigDecimal prijs,
        List<String> categorieNamen,
        String stockLabel,
        boolean beschikbaar
) {}