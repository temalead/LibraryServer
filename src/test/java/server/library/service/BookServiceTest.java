package server.library.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import server.library.domain.Book;
import server.library.domain.Genre;
import server.library.domain.Library;
import server.library.domain.dto.CreateBookDto;
import server.library.domain.dto.UpdateBookDto;
import server.library.exception.BookNotFoundException;
import server.library.exception.LibraryNotExistingException;
import server.library.repository.BookRepository;
import server.library.repository.LibraryRepository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;
    @Mock
    private LibraryRepository libraryRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    @Test
    void shouldReturnNewBook() {
        Library library = new Library().setId(1L);
        CreateBookDto createBookDto = new CreateBookDto();
        createBookDto
                .setName("Holy Bible")
                .setLibrary(1L);
        Book book = new Book()
                .setName(createBookDto.getName())
                .setLibrary(library);
        when(libraryRepository.findById(1L)).thenReturn(Optional.of(library));

        Book result = bookService.addBook(createBookDto);

        assertEquals(book, result);
        verify(libraryRepository).findById(1L);
        verify(bookRepository).save(result);

    }

    @Test
    void shouldThrowLibraryNotExistingException() {
        CreateBookDto createdBook = new CreateBookDto()
                .setLibrary(1337L)
                .setName("Bible");

        when(libraryRepository.findById(1337L)).thenReturn(Optional.empty());

        assertThrows(LibraryNotExistingException.class, () -> bookService.addBook(createdBook));

        verifyNoInteractions(bookRepository);
    }


    @Test
    void shouldReturnExistingBook() throws NoSuchMethodException {
        Book existingBook = new Book().setName("Bible")
                .setAuthors(Set.of("God"));
        when(bookRepository.findByParams(existingBook.getName(), existingBook.getAuthors()))
                .thenReturn(List.of(existingBook));

        List<Book> bookByParams = bookService.getBooksByParams(existingBook.getName(), existingBook.getAuthors());
        assertEquals(List.of(existingBook), bookByParams);
    }

    @Test
    void shouldReturnBookNotFoundExceptionCauseParams() {
        when(bookRepository.findByParams("Not", Set.of("Today"))).thenReturn(List.of());

        assertThrows(BookNotFoundException.class, () -> bookService.getBooksByParams("Not", Set.of("Today")));
    }

    @Test
    void getBooksByGenres() throws NoSuchMethodException {
        Book existingBook = new Book()
                .setGenres(Set.of(Genre.NOVEL));
        when(bookRepository.findByGenres(Set.of(Genre.NOVEL))).thenReturn(List.of(existingBook));

        List<Book> result = bookService.getBooksByGenres(Set.of("NOVEL"));
        assertEquals(List.of(existingBook), result);
    }

    @Test
    void shouldReturnUpdatedBook() {
        Book book = new Book()
                .setId(1L)
                .setName("Seller")
                .setAuthors(Set.of("Phil Night"));

        UpdateBookDto updateBookDto = new UpdateBookDto()
                .setAuthors(Set.of("Pheel"))
                .setDescription("Bla-bla")
                .setBestseller(true)
                .setDate(Date.valueOf("1988-01-01"));
        Book expected = book
                .setAuthors(Set.of("Pheel"))
                .setDescription("Bla-bla")
                .setBestseller(true)
                .setDateOfCreation(Date.valueOf("1988-01-01"));

        when(bookRepository.findById(any())).thenReturn(Optional.of(book));

        Book updateBook = bookService.updateBook(book.getId(), updateBookDto);

        assertEquals(expected, updateBook);

        verify(bookRepository).findById(book.getId());

    }
}