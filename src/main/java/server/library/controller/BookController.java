package server.library.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import server.library.domain.Book;
import server.library.domain.Genre;
import server.library.domain.dto.CreateBookDto;
import server.library.service.BookService;

import java.util.Date;
import java.util.Set;

@RestController
@RequestMapping("/library")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody @Validated CreateBookDto book) {
        return new ResponseEntity<>(bookService.addBook(book), HttpStatus.CREATED);
    }


    @GetMapping(value = "/book",params = {"name","author","genres","date_of_creation"})
    public ResponseEntity<Book> getBookByParams(@RequestParam String name,
                                              @RequestParam Set<String> author,
                                              @RequestParam Set<Genre> genres,
                                              @RequestParam Date date_of_creation){

        bookService.getBook(name,author,genres,date_of_creation);
        return null;
    }
}
