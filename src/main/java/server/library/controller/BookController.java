package server.library.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.library.domain.Book;
import server.library.service.BookService;

@RestController
@RequestMapping("/library")
@RequiredArgsConstructor
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping
    public ResponseEntity<Book> getBooks(@RequestBody Book book){
        bookService.addBook(book);
        return ResponseEntity.ok(book);
    }

    @GetMapping("/book")
    public ResponseEntity<Book> getBookByName(){
        return null;
    }
}
