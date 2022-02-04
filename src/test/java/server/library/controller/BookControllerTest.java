package server.library.controller;

import org.junit.ClassRule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;
import server.library.domain.Genre;
import server.library.domain.dto.CreateBookDto;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

class BookControllerTest extends SpringTestConfig {

    @ClassRule
    public static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:11.1")
            .withDatabaseName("testdatabase")
            .withUsername("sa")
            .withPassword("sa");
    static class Initializer
            implements ApplicationContextInitializer {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + postgreSQLContainer.getJdbcUrl(),
                    "spring.datasource.username=" + postgreSQLContainer.getUsername(),
                    "spring.datasource.password=" + postgreSQLContainer.getPassword()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }

    private final String url = "/library";

    @Autowired
    private BookController bookController;


    @Test
    void addBook() throws Exception {
        CreateBookDto requestedBook = new CreateBookDto()
                .setLibrary(1L)
                .setName("Test")
                .setAuthors(Set.of("Test"))
                .setBestseller(true)
                .setGenres(Set.of(Genre.NOVEL));
        MockHttpServletResponse response = mockMvc.perform(
                post(url).contentType(MediaType.APPLICATION_JSON).content(
                        mapToJson(requestedBook))
        ).andReturn().getResponse();

        assertEquals(HttpStatus.CREATED.value(),response.getStatus());
    }

    @Test
    void getBookByNameAndAuthors() {

    }

    @Test
    void getBooksByGenres() {
    }
}