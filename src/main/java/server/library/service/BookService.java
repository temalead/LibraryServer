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
        log.info("Added new book with name{} in library {}",book.getName(),book.getLibrary().getId());
        return bookRepository.save(book);
    }

    public Book getBookByName(String bookName) {
        Optional<Book> foundBook = bookRepository.findByName(bookName);
        return  foundBook.orElseThrow(()->new BookNotFoundException(bookName));
    }

    public List<Book> getBook(String name, Set<String> authors) {
        log.info("Searching for a book with name {} and authors {}",name,authors);
        List<Book>result = bookRepository.findByParams(name, authors);
        return getBookList(name, result);
    }

    private List<Book> getBookList(String name, List<Book> result) {
        if (!(result.size()==0)){
            return result;
        }else{
            throw new BookNotFoundException(name);
        }
    }

    public List<Book> getBooksByGenres(Set<Genre> genres) {
        List<Book> result = bookRepository.findByGenres(genres);
        log.info("Finding books with genres {}",genres.toString());
        return getBookList(genres.toString(), result);
    }
}
