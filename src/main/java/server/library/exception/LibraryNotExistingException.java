package server.library.exception;

public class LibraryNotExistingException extends RuntimeException {
    public LibraryNotExistingException(long libraryId) {
        super("Library not found: "+libraryId);
    }
}
