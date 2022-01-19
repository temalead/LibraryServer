package server.library.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.library.domain.Book;
import server.library.repository.BookRepository;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public void addBook(Book book){
        Optional<Book> repository = bookRepository.findByName(book.getName());
        if (repository.isEmpty()){
            log.info("Book with {} isn`t in library. Adding...",book.getName());
            bookRepository.save(book);
            log.info("Book was added");
        }
    }
}
