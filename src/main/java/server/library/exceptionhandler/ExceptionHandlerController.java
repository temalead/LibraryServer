package server.library.exceptionhandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import server.library.exception.BookNotFoundException;
import server.library.exception.LibraryExistByAddressException;
import server.library.exception.LibraryNotExistingException;

import java.net.BindException;

@RestControllerAdvice
@Slf4j
public class ExceptionHandlerController {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({BookNotFoundException.class, LibraryNotExistingException.class})
    public ResponseEntity<String> throwNotFoundExceptionToUser(RuntimeException ex) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(LibraryExistByAddressException.class)
    public ResponseEntity<String> throwForbiddenLibrary(RuntimeException e){
        log.error(e.getMessage());
        return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
    }
}
