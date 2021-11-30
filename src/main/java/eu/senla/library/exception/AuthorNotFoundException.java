package eu.senla.library.exception;

public class AuthorNotFoundException extends Exception {
    public AuthorNotFoundException(Long id) {
        super("author with id: " + id + " was not found");
    }
}
