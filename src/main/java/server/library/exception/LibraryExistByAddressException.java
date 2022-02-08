package server.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class LibraryExistByAddressException extends RuntimeException {
    public LibraryExistByAddressException(String message) {
        super("Library with "+message+" already exists!");
    }
}
