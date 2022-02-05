package server.library.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import server.library.domain.Book;

import javax.persistence.EntityManager;
import java.util.List;

@AutoConfigureMockMvc
@Sql(value = "/test/migration/INSERT_BOOKS.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/test/migration/AFTER_TESTS.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public abstract class SpringTestConfig {

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
}
