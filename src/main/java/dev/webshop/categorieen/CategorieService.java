package dev.webshop.categorieen;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
class CategorieService {
    private final CategorieRepository categorieRepository;

    CategorieService(CategorieRepository categorieRepository) {
        this.categorieRepository = categorieRepository;
    }

    List<CategorieDto> findAll() {
        return categorieRepository.findAll()
                .stream()
                .map(categorie -> new CategorieDto(
                        categorie.getCategorieId(),
                        categorie.getNaam(),
                        categorie.getHoofdCategorieId()
                ))
                .toList();
    }
}