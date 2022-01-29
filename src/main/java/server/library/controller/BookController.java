package server.library.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.library.domain.Book;
import server.library.domain.dto.CreateBookDto;
import server.library.service.BookService;

@RestController
@RequestMapping("/library")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @PostMapping
    public ResponseEntity<Book> getBooks(@RequestBody @Validated CreateBookDto book){
        return new ResponseEntity<>(bookService.addBook(book), HttpStatus.CREATED);
    }

    /*@GetMapping("/search/book")
    public ResponseEntity<Book> getBookByName(@RequestParam String book){
        Book foundBook = bookService.getBookByName(book);
        return ResponseEntity.ok()
                .body(foundBook);
    }*/
}
