package server.library.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import server.library.domain.Book;
import server.library.domain.Genre;
import server.library.domain.Library;
import server.library.domain.dto.CreateBookDto;
import server.library.exception.BookNotFoundException;
import server.library.exception.LibraryNotExistingException;
import server.library.repository.BookRepository;
import server.library.repository.LibraryRepository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final LibraryRepository libraryRepository;

    @Transactional
    public Book addBook(CreateBookDto bookDto){
        long libraryId = bookDto.getLibrary();
        Library existingLibrary = libraryRepository.findById(libraryId)
                .orElseThrow(()->new LibraryNotExistingException(libraryId));
        Book book = new Book()
                .setName(bookDto.getName())
                .setLibrary(existingLibrary)
                .setGenres(bookDto.getGenres())
                .setAuthors(bookDto.getAuthors())
                .setPublishers(bookDto.getPublishers())
                .setDescription(bookDto.getDescription())
                .setDateOfCreation(bookDto.getDateOfCreation());
        return bookRepository.save(book);
    }

    public Book getBookByName(String bookName) {
        Optional<Book> foundBook = bookRepository.findByNameStartsWith(bookName);
        return  foundBook.orElseThrow(()->new BookNotFoundException(bookName));
    }

    public List<Book> getBook(String name, Set<String> authors) {
        return bookRepository.findByParams(name,authors).orElseThrow(()->new BookNotFoundException(name));
    }
}
