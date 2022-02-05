package server.library.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import server.library.domain.Book;
import server.library.domain.dto.CreateBookDto;
import server.library.domain.dto.UpdateBookDto;
import server.library.service.BookService;

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


    @GetMapping("/search")
    public ResponseEntity<List<Book>> getBookByNameAndAuthors(@RequestParam(required = false) String name,
                                                              @RequestParam(required = false) Set<String> authors) throws NoSuchMethodException {
        return ResponseEntity.ok(bookService.getBooksByParams(name, authors));
    }

    @GetMapping("/search/genres")
    public ResponseEntity<List<Book>> getBooksByGenres(@RequestParam Set<String> genres) throws NoSuchMethodException {
        return ResponseEntity.ok(bookService.getBooksByGenres(genres));
    }

    @PutMapping("/book/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable("id") Long id,
                                           @RequestBody UpdateBookDto book) {
        return new ResponseEntity<>(bookService.updateBook(id, book), HttpStatus.OK);
    }
}
