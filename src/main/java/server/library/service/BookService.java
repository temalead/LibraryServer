package server.library.service;

import server.library.domain.Book;
import server.library.domain.dto.CreateBookDto;
import server.library.domain.dto.UpdateBookDto;

import java.util.List;
import java.util.Set;

public interface BookService {
    Book addBook(CreateBookDto book);

    List<Book> getBooksByParams(String name, Set<String> authors) throws NoSuchMethodException;

    List<Book> getBooksByGenres(Set<String> genres) throws NoSuchMethodException;

    Book updateBook(Long id, UpdateBookDto book);
}
