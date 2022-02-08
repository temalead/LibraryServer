package server.library.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import server.library.domain.Book;

import javax.persistence.EntityManager;
import java.util.List;

@AutoConfigureMockMvc
@Sql(value = "/test/migration/INSERT_BOOKS.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/test/migration/AFTER_TESTS.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
public abstract class SpringTestConfig {

    @Container
    public static PostgreSQLContainer postgreSQLContainer=new PostgreSQLContainer("postgres:14.1")
            .withDatabaseName("integreation-tests-db")
            .withUsername("sa")
            .withPassword("sa");
    static class Initializer
            implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + postgreSQLContainer.getJdbcUrl(),
                    "spring.datasource.username=" + postgreSQLContainer.getUsername(),
                    "spring.datasource.password=" + postgreSQLContainer.getPassword()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }

    @Autowired
    protected MockMvc mvc;
    @Autowired
    protected Gson gson;

    @Autowired
    protected EntityManager session;


    protected String mapToJson(Object obj) throws JsonProcessingException {
        return gson.toJson(obj);
    }

    protected List<Book> mapToBookList(String source) throws JsonProcessingException {
        Book[] books = gson.fromJson(source, Book[].class);
        return List.of(books);
    }

    protected Book mapToBook(String source) {
        return gson.fromJson(source, Book.class);
    }
}
