package server.library.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import server.library.domain.dto.CreateBookDto;
import server.library.service.BookService;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class BookControllerTest extends SpringTestConfig{

    @MockBean
    private BookService service;


    @Test
    void shouldReturnNewBook() throws Exception {
        CreateBookDto requestedBook = new CreateBookDto()
                .setLibrary(1L)
                .setName("test")
                .setAuthors(Set.of("test"));

        mvc.perform(post("/library")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapToJson(requestedBook)))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void shouldReturnLibraryNotFoundException() throws Exception {
        CreateBookDto requestedBook = new CreateBookDto()
                .setLibrary(1337L)
                .setName("test")
                .setAuthors(Set.of("test"));
        String response = mvc.perform(post("/library")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapToJson(requestedBook)))
                .andReturn().getResponse().getContentAsString();
        System.out.println(response);
        assertEquals(requestedBook,mapToCreateBook(response));
    }

    @Test
    void shouldReturnBooksByCorrectParametersNameAndAuthor() throws Exception {
        String request="?author=Test&name=Test";

        String response = mvc.perform(get("/search/params".concat(request)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();
        System.out.println(response);


    }

    @Test
    void getBooksByGenres() {
    }
}