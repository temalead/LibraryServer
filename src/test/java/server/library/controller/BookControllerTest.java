package server.library.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import server.library.domain.Book;
import server.library.domain.dto.CreateBookDto;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class BookControllerTest extends SpringTestConfig {
    private final String url = "/library";


    @Test
    @DisplayName("Should Return books By CorrectParameters Name and Author")
    void shouldReturnBooksByCorrectParametersNameAndAuthor() throws Exception {
        String request = "?author=Test&name=Test";

        String response = mvc.perform(get(url.concat("/search").concat(request)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        List<Book> responseBooks = mapToBookList(response);
        List<Book> expectedBooks = session.createQuery("select b from Book b where b.name='Test'").getResultList();
        assertEquals(expectedBooks.size(),responseBooks.size());
    }

    @Test
    @DisplayName("Should Return NewBook")
    void shouldReturnNewBook() throws Exception {
        CreateBookDto request = new CreateBookDto()
                .setLibrary(1L)
                .setName("TestB")
                .setAuthors(Set.of("TestB"));

        String response = mvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapToJson(request)))
                .andExpect(status().is2xxSuccessful())
                .andReturn().getResponse().getContentAsString();
        Book responseBook = mapToBook(response);

        assertEquals(request.getName(), responseBook.getName());
    }

    @Test
    @DisplayName("Should Return LibraryNotFoundException")
    void shouldReturnLibraryNotFoundException() throws Exception {
        CreateBookDto request = new CreateBookDto()
                .setLibrary(1337L)
                .setName("TestB")
                .setAuthors(Set.of("TestB"));
        mvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapToJson(request)))
                .andExpect(status().isNotFound());

    }


    @Test
    @DisplayName("Should Return Books By Correct Genres")
    void shouldReturnBooksByCorrectGenres() throws Exception {


        String response = mvc.perform(get(url.concat("/search/genres?genres=NOVEL")))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        List<Book> books = mapToBookList(response);

        assertEquals(1,books.size());

    }
}