package server.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.library.domain.Book;
import server.library.repository.BookRepository;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public void addBook(Book book){
        Book save = bookRepository.save(book);
        System.out.println(save);
    }
}
