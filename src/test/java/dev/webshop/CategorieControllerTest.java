package dev.webshop;

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
public class CategorieControllerTest {

    private final MockMvcTester mockMvcTester;
    private final JdbcClient jdbcClient;
    private final static String CATEGORIEEN = "Categorieen";

    public CategorieControllerTest(MockMvcTester mockMvcTester, JdbcClient jdbcClient) {
        this.mockMvcTester = mockMvcTester;
        this.jdbcClient = jdbcClient;
    }

    /*
    ================ EXPECTED BEHAVIOR TESTS =================
    */

    @Test
    @DisplayName("/api/categorieen geeft juiste aantal")
    void aantalCategorieenGeeftJuisteAantalTerug () {
        var response = mockMvcTester
                .get()
                .uri("/api/categorieen");

        assertThat(response)
                .hasStatusOk()
                .bodyJson()
                .extractingPath("length()")
                .isEqualTo(JdbcTestUtils.countRowsInTable(jdbcClient, CATEGORIEEN));
    }
}