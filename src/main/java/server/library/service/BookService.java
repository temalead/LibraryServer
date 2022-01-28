package server.library.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import server.library.domain.Book;
import server.library.domain.Library;
import server.library.domain.dto.CreateBookDto;
import server.library.exception.LibraryNotExistingException;
import server.library.repository.BookRepository;
import server.library.repository.LibraryRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final LibraryRepository libraryRepository;

    @Transactional
    public Book addBook(CreateBookDto bookDto){
        long libraryId = bookDto.getLibraryDto().getId();
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

    public Book getBookByName(String book) {
        Optional<Book> foundBook = bookRepository.findByNameStartsWith(book);
        return  foundBook.orElseThrow();
    }
}
