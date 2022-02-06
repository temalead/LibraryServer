package server.library.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import server.library.domain.Book;
import server.library.domain.Genre;
import server.library.domain.Library;
import server.library.domain.dto.CreateBookDto;
import server.library.domain.dto.UpdateBookDto;
import server.library.exception.BookNotFoundException;
import server.library.exception.LibraryNotExistingException;
import server.library.repository.BookRepository;
import server.library.repository.LibraryRepository;
import server.library.service.utils.MessageErrorCreator;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookService{
    private final BookRepository bookRepository;
    private final LibraryRepository libraryRepository;

    @Transactional
    public Book addBook(CreateBookDto book){
        long libraryId = book.getLibrary();
        Library existingLibrary = libraryRepository.findById(libraryId)
                .orElseThrow(()->new LibraryNotExistingException(libraryId));

        Book newBook = new Book()
                .setName(book.getName())
                .setLibrary(existingLibrary)
                .setGenres(book.getGenres())
                .setAuthors(book.getAuthors())
                .setPublishers(book.getPublishers())
                .setDescription(book.getDescription())
                .setDateOfCreation(book.getDate_of_creation());
        log.info("Added new book with name {} in libraryId {}",newBook.getName(),newBook.getLibrary().getId());
        bookRepository.save(newBook);
        return newBook;
    }


    public List<Book> getBooksByParams(String name, Set<String> authors) throws NoSuchMethodException {

        log.info("Searching for a book with name {} and authors {}",name,authors);
        List<Book>result = bookRepository.findByParams(name, authors);

        return getBooks(result, MessageErrorCreator.makeErrorMessage("getBooksByParams",List.of(Optional.ofNullable(name), Optional.ofNullable(authors))));
    }



    public List<Book> getBooksByGenres(Set<String> genres) throws NoSuchMethodException {
        log.info("Searching for a books with genres {}",genres);

        Set<Genre> genreSet = Genre.filterRequestGenres(genres);

        List<Book> result = bookRepository.findByGenres(genreSet);


        return getBooks(result,MessageErrorCreator.makeErrorMessage("getBooksByGenres", List.of(Optional.of(genres))));
    }

    @Transactional
    public Book updateBook(Long id, UpdateBookDto requestBook) {
        Book updatableBook = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(String.valueOf(id)));
        updatableBook.setName(requestBook.getName())
                .setAuthors(requestBook.getAuthors())
                .setGenres(requestBook.getGenres())
                .setDescription(requestBook.getDescription())
                .setBestseller(requestBook.isBestseller())
                .setDateOfCreation(requestBook.getDate_of_creation())
                .setPublishers(requestBook.getPublishers());

        log.info("Updated book with id {} to {}", id,requestBook);
        return updatableBook;
    }


    private List<Book> getBooks(List<Book> result, String possibleError) {
        if (result.size()!=0){
            return result;
        }
        else{
            throw new BookNotFoundException(possibleError);
        }
    }
}
