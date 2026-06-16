package dev.webshop.artikelen;

import dev.webshop.categorieen.Categorie;
import dev.webshop.categorieen.CategorieRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@Transactional(readOnly = true)
public class ArtikelService {
    private final ArtikelRepository artikelRepository;
    private final CategorieRepository categorieRepository;

    public ArtikelService(ArtikelRepository artikelRepository, CategorieRepository categorieRepository) {
        this.artikelRepository = artikelRepository;
        this.categorieRepository = categorieRepository;
    }

    private ArtikelDto mapArtikelToDTO(Artikel artikel) {
        return new ArtikelDto(
                artikel.getArtikelId(),
                artikel.getNaam(),
                artikel.getBeschrijving(),
                artikel.getPrijs(),
                artikel.getCategorieen()
                        .stream()
                        .map(Categorie::getNaam)
                        .toList()
                ,getStockLabel(artikel.getVoorraad())
                ,isBeschikbaar(artikel.getVoorraad())
        );
    }

    long findAantal() {
        return artikelRepository.count();
    }

    Page<ArtikelDto> findAllPaged(Pageable pageable) throws ArtikelNietGevondenException {
        return artikelRepository.findAll(pageable)
                .map(this::mapArtikelToDTO);
    }

    public ArtikelDto findById(Long id) {
        var artikel = artikelRepository.findById(id)
                .orElseThrow(ArtikelNietGevondenException::new);

        return mapArtikelToDTO(artikel);
    }

    Page<ArtikelDto> findByCategorieIdPaged(long categorieId, Pageable pageable) {
        boolean isHoofdCategorie = categorieRepository.existsByHoofdCategorieId(categorieId);
        boolean isValidCategorie = categorieRepository.existsByCategorieId(categorieId);

        if (isHoofdCategorie) {
            return Page.empty(pageable);
        }

        if (!isValidCategorie) {
            return Page.empty(pageable);
        }

        return artikelRepository.findByCategorieenCategorieId(categorieId, pageable)
                .map(this::mapArtikelToDTO);
    }

    private String getStockLabel(long voorraad) {
        if (voorraad <= 0) return "Uitverkocht";
        if (voorraad <= 5) return "Bijna uitverkocht";
        return "Op voorraad";
    }

    private boolean isBeschikbaar(long voorraad) {
        return voorraad > 0;
    }

    public List<ArtikelDto> findRandom10() {

        long count = artikelRepository.count();
        if (count == 0) return List.of();

        Random random = new Random();
        List<ArtikelDto> result = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            int page = random.nextInt((int) count);

            var artikelPage = artikelRepository.findAll(PageRequest.of(page, 1));

            artikelPage.stream()
                    .findFirst()
                    .map(this::mapArtikelToDTO)
                    .ifPresent(result::add);
        }

        return result;
    }
}