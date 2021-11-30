package eu.senla.library.exception;

public class BookingNotFoundException extends Exception {
    public BookingNotFoundException(Long id) {
        super("booking with id: " + id + " was not found");
    }
}
