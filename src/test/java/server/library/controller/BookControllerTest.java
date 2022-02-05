package server.library.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import server.library.domain.dto.CreateBookDto;

import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class BookControllerTest extends SpringTestConfig{




    @Test
    void shouldReturnBooksByCorrectParametersNameAndAuthor() throws Exception {
        String request="?author=Test&name=Test";

        String response = mvc.perform(get("/library/search/params".concat(request)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(response);


    }

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
    }



    @Test
    void getBooksByGenres() {
    }
}