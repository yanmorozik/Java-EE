package eu.senla.library.exception;

import lombok.Getter;

@Getter
public class AuthorNotFoundException extends Exception{

    public AuthorNotFoundException(Long id) {
       super("author with id: " + id + " was not found");
    }

}
