package dev.webshop.categorieen;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
@RequestMapping("api/categorieen")
class CategorieController {
    private final CategorieService categorieService;

    CategorieController(CategorieService categorieService) {
        this.categorieService = categorieService;
    }

    @GetMapping
    List<CategorieDto> findAll() {
        return categorieService.findAll();
    }
}