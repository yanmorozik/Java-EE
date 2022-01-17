package eu.senla.library.filter;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class AuthorsProtocol implements Protocol {
    private final String
            firstName,
            surname;

    public AuthorsProtocol(String firstName, String surname) {
        this.firstName = firstName;
        this.surname = surname;
    }
}
