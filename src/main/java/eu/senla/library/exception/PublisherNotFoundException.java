package eu.senla.library.exception;

public class PublisherNotFoundException extends Exception {
    public PublisherNotFoundException(Long id) {
        super("publisher with id: " + id + " was not found");
    }
}
