package eu.senla.library.exception;

public class GenreNotFoundException extends Exception {
    public GenreNotFoundException(Long id) {
        super("genre with id: " + id + " was not found");
    }
}
