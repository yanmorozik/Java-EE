package eu.senla.library.exception;

public class LanguageNotFoundException extends Exception {
    public LanguageNotFoundException(Long id) {
        super("language with id: " + id + " was not found");
    }
}
