package eu.senla.library.exception;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(Long id) {
        super("user with id: " + id + " was not found");
    }
}
