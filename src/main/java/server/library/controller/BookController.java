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
import java.util.List;
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


    @GetMapping(value = "/book",params = {"name","author"})
    public ResponseEntity<List<Book>> getBookByNameAndAuthors(@RequestParam String name,
                                                              @RequestParam Set<String> author){
        return ResponseEntity.ok(bookService.getBook(name,author));
    }
}
