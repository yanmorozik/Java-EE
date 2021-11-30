package eu.senla.library.exception;

public class RoleNotFoundException extends Exception{
    public RoleNotFoundException(Long id) {
        super("role with id: " + id + " was not found");
    }
}
