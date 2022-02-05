package server.library.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.junit.jupiter.Testcontainers;
import server.library.domain.dto.CreateBookDto;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
@ActiveProfiles("test")
public abstract class SpringTestConfig {

    @Autowired
    protected Gson gson;

    @Autowired
    protected MockMvc mvc;

    public CreateBookDto mapToCreateBook(String book) throws JsonProcessingException {
        return gson.fromJson(book, CreateBookDto.class);
    }

    public String mapToJson(CreateBookDto obj) {
        return gson.toJson(obj);
    }
}
