package eu.senla.library.exception;

public class CredentialNotFoundException extends Exception{
    public CredentialNotFoundException(Long id) {
        super("credential with id: " + id + " was not found");
    }
}
