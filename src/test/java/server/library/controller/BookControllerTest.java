package server.library.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.shaded.com.github.dockerjava.core.MediaType;
import server.library.domain.Book;
import server.library.domain.Genre;
import server.library.domain.dto.CreateBookDto;
import server.library.service.BookService;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class BookControllerTest extends SpringTestConfig {

    private final String url = "/library";

    @Autowired
    private BookController bookController;


    @Test
    void addBook() throws Exception {
        CreateBookDto requestedBook = new CreateBookDto()
                .setLibrary(1L)
                .setName("Good book")
                .setAuthors(Set.of("P"))
                .setBestseller(true)
                .setGenres(Set.of(Genre.NOVEL));
        String response = mockMvc.perform(MockMvcRequestBuilders.post(url)
                        .contentType(String.valueOf(MediaType.APPLICATION_JSON)).content(mapToJson(requestedBook)))
                .andReturn().getResponse().getContentAsString();

        bookController.addBook(requestedBook);

        CreateBookDto result = mapToCreateBook(response);

        assertEquals(requestedBook,result);
    }

    @Test
    void getBookByNameAndAuthors() {
    }

    @Test
    void getBooksByGenres() {
    }
}