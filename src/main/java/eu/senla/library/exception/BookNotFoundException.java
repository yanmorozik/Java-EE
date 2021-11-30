package eu.senla.library.exception;

public class BookNotFoundException extends Exception {
    public BookNotFoundException(Long id) {
        super("book with id: " + id + " was not found");
    }
}
