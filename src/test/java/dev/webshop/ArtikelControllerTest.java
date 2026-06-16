package dev.webshop;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
public class ArtikelControllerTest {

    private final MockMvcTester mockMvcTester;
    private final JdbcClient jdbcClient;
    private final static String ARTIKELEN = "Artikelen";

    public ArtikelControllerTest(MockMvcTester mockMvcTester, JdbcClient jdbcClient) {
        this.mockMvcTester = mockMvcTester;
        this.jdbcClient = jdbcClient;
    }

    /*
    ================ EDGE TESTS =================
    */

    @Test
    @DisplayName("/api/artikel/{id} geeft exception als artikel niet bestaat")
    void apiArtikelBestaatNietGeeftException () {

        var response = mockMvcTester
                .get()
                .uri("/api/artikelen/artikel/" + Long.MAX_VALUE)
                .exchange();

        var status = response.getResponse().getStatus();

        assertEquals(404, status);
    }


    /*
    ================ EXPECTED BEHAVIOR TESTS =================
    */

    @Test
    @DisplayName("/api/artikelen/aantal geeft juiste aantal")
    void aantalArtikelenGeeftJuisteAantalTerug () {
        var response = mockMvcTester
                .get()
                .uri("/api/artikelen/aantal");

        assertThat(response)
                .hasStatusOk()
                .bodyJson()
                .extractingPath("$")
                .isEqualTo(JdbcTestUtils.countRowsInTable(jdbcClient, ARTIKELEN));
    }

    /*
    ================ LOCAL-ONLY TESTS =================
    Deze tests gebruiken hardcoded values en veronderstellen
    dat bepaalde data in de lokale database aanwezig is.
    Ze zijn niet geschikt voor real-time omgevingen.
    ===================================================
    */

    int idVanArtikel () {
        var sql = """
                select artikelId
                from Artikelen
                where ean = ?
                """;

        //hard-coded
        var ean = "5499999000019";

        return jdbcClient
                .sql(sql)
                .param(ean)
                .query(Integer.class)
                .single();
    }


    /*
    ================ EXPECTED BEHAVIOR TESTS =================
    */

    @Test
    @DisplayName("/api/artikel/{id} geeft het juiste artikel indien het bestaat")
    void apiArtikelGeeftJuisteArtikel () {
        //hard-coded id
        var idVanArtikel = idVanArtikel();

        var response = mockMvcTester
                .get()
                .uri("/api/artikelen/artikel/" + idVanArtikel);

        assertThat(response)
                .hasStatusOk()
                .bodyJson()
                .extractingPath("artikelId")
                .isEqualTo(idVanArtikel);
    }
}