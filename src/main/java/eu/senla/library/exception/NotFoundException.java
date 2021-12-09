package eu.senla.library.exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException(Long id) {
        super(("entity with id: " + id + " was not found"));
    }
}
