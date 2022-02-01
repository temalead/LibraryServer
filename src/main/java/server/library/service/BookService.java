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
        bookRepository.save(book);
        return book;
    }


    @GetParameters(value="getBookByParams")
    public List<Book> getBookByParams(String name, Set<String> authors) throws NoSuchMethodException {

        log.info("Searching for a book with name {} and authors {}",name,authors);
        List<Book>result = bookRepository.findByParams(name, authors);

        List<String> parameters = ParameterInterceptor.getParametersFromMethod(BookService.class, "getBookByParams");
        List<Optional<Object>> list = List.of(Optional.ofNullable(name), Optional.ofNullable(authors));

        return getBooks(result,MessageErrorCreator.makeErrorMessage(parameters,list));
    }



    @GetParameters(value = "getBooksByGenres")
    public List<Book> getBooksByGenres(Set<String> genres) throws NoSuchMethodException {
        log.info("Searching for a books with genres {}",genres);

        Set<Genre> genreSet = Genre.filterRequestGenres(genres);

        List<Book> result = bookRepository.findByGenres(genreSet);

        List<String> parameters = ParameterInterceptor.getParametersFromMethod(BookService.class, "getBooksByGenres");
        List<Optional<Object>> list = List.of(Optional.of(genres));


        return getBooks(result,MessageErrorCreator.makeErrorMessage(parameters, list));
    }


    private List<Book> getBooks( List<Book> result, String params) {
        if (result.size()!=0){
            return result;
        }
        else{
            throw new BookNotFoundException(params);
        }
    }
}
