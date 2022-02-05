package server.library.exception;

public class LibraryExistByAddressException extends RuntimeException {
    public LibraryExistByAddressException(String message) {
        super("Library with "+message+" already exists!");
    }

    public LibraryExistByAddressException(String message, Throwable cause) {
        super(message, cause);
    }

    public LibraryExistByAddressException(Throwable cause) {
        super(cause);
    }
}
