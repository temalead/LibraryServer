package server.library.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import server.library.domain.dto.CreateBookDto;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

@Sql(value = "/test/migration/INSERT_BOOKS.sql",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/test/migration/AFTER_TESTS.sql",executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public abstract class SpringTestConfig {
    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private Gson gson;

    public CreateBookDto mapToCreateBook(String book) throws JsonProcessingException {
        return gson.fromJson(book, CreateBookDto.class);
    }

    public String mapToJson(CreateBookDto obj){
        return gson.toJson(obj);
    }
}
