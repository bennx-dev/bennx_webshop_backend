package dev.webshop.categorieen;

public record CategorieDto(
        Long categorieId,
        String naam,
        Long hoofdCategorieId
) {}